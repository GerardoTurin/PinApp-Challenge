package trackapp.icube04backend.infrastructure.adapters.client_module.restcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import trackapp.icube04backend.common.messaging.publishers.ClientEventPublisher;
import trackapp.icube04backend.infrastructure.configuration.security.jwt.filters.JwtFilter;
import trackapp.icube04backend.modules.client_module.domain.models.Client;
import trackapp.icube04backend.modules.client_module.domain.ports.usecases.*;


import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ClientRestController.class)
@AutoConfigureMockMvc(addFilters = false) // evita que se añadan filtros de seguridad reales
@Import({ClientRestControllerCreateTest.MethodSecurityOnly.class,
        ClientRestControllerCreateTest.SecurityAdviceForTests.class})
class ClientRestControllerCreateTest {

    @TestConfiguration
    @EnableMethodSecurity // activo @PreAuthorize en el slice de test
    static class MethodSecurityOnly {}

    @RestControllerAdvice
    static class SecurityAdviceForTests {
        @ExceptionHandler(org.springframework.security.authorization.AuthorizationDeniedException.class)
        @ResponseStatus(HttpStatus.FORBIDDEN)
        void onDenied() {}
    }


    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;


    // Evita que Spring intente construir el filtro real
    @MockBean
    JwtFilter jwtFilter;

    // Dependencias del controller:
    @MockBean
    ICreateClientUseCase createClientUseCase;
    @MockBean
    IFindClientUseCase findClientUseCase;
    @MockBean
    IUpdateClientUseCase updateClientUseCase;
    @MockBean
    IDeleteClientUseCase deleteClientUseCase;
    @MockBean
    IGetClientStatsUseCase getClientStatsUseCase;
    @MockBean
    IFindClientsWithDerivedUseCase listClientsWithDerivedUseCase;
    @MockBean
    ClientEventPublisher clientEventPublisher;

    // ---------- helpers ----------
    private String newClientJson() throws Exception {
        // payload que tu controller espera (ajústalo a tu CreateClientRequest)
        var payload = new java.util.HashMap<String,Object>();
        payload.put("firstName", "Ana");
        payload.put("lastName", "Pérez");
        payload.put("age", 32);
        payload.put("dateOfBirth", "1993-07-10");
        payload.put("userId", 99); // tu caso de uso lo valida
        return om.writeValueAsString(payload);
    }

    private static ArgumentMatcher<Client> hasData(String fn, String ln, int age, LocalDate dob, long userId) {
        return c -> c != null
                && fn.equals(c.getFirstName())
                && ln.equals(c.getLastName())
                && age == c.getAge()
                && dob.equals(c.getDateOfBirth())
                && userId == c.getUserId();
    }





    // ---------- tests ----------

    @Test
    @WithMockUser(authorities = "SOMAS_SUPER_ADMIN")
    void create_ok_returns201() throws Exception {
        // arrange
        var json = newClientJson();

        // El usecase devuelve el cliente creado
        var created = Client.builder()
                .id(123L)
                .firstName("Ana")
                .lastName("Pérez")
                .age(32)
                .dateOfBirth(LocalDate.parse("1993-07-10"))
                .userId(99L)
                .companyId(2L)
                .build();
        when(createClientUseCase.execute(argThat(
                hasData("Ana","Pérez",32, LocalDate.parse("1993-07-10"), 99L)
        ))).thenReturn(created);

        mvc.perform(post("/clients")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(123))
                .andExpect(jsonPath("$.firstName").value("Ana"))
                .andExpect(jsonPath("$.lastName").value("Pérez"));

        verify(createClientUseCase, times(1)).execute(any());
        verify(clientEventPublisher, times(1)).publishClientCreated(any(Client.class));
    }




    @Test
    @WithMockUser(authorities = "SOMAS_USER") // NO cumple
    void create_forbidden_whenNotSuperAdmin() throws Exception {
        var json = newClientJson();

        mvc.perform(post("/clients")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isForbidden());

        verifyNoInteractions(createClientUseCase);
    }




    @Test
    @WithMockUser(authorities = "SOMAS_SUPER_ADMIN")
    void create_badRequest_whenUseCaseValidationsFail() throws Exception {
        var json = newClientJson();

        // simula que el caso de uso valida y rechaza (tu excepción real)
        var ex = new trackapp.icube04backend.common.exceptions.BadRequestExceptionService(
                "La edad del Cliente no puede ser vacia o negativa"
        );
        when(createClientUseCase.execute(any())).thenThrow(ex);

        mvc.perform(post("/clients")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string(
                        org.hamcrest.Matchers.containsString("La edad del Cliente no puede ser vacia o negativa")
                ));
    }
}

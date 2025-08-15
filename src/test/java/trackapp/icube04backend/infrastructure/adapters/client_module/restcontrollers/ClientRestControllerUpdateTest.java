package trackapp.icube04backend.infrastructure.adapters.client_module.restcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.common.exceptions.GenericMessages;
import trackapp.icube04backend.common.exceptions.NotFoundExceptionService;
import trackapp.icube04backend.common.messaging.publishers.ClientEventPublisher;
import trackapp.icube04backend.infrastructure.configuration.security.jwt.filters.JwtFilter;
import trackapp.icube04backend.modules.client_module.domain.models.Client;
import trackapp.icube04backend.modules.client_module.domain.ports.usecases.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ClientRestController.class)
@AutoConfigureMockMvc(addFilters = false) // sin filtros de seguridad reales
class ClientRestControllerUpdateTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;

    // evitar construir el filtro real
    @MockBean JwtFilter jwtFilter;

    // dependencias del controller
    @MockBean ICreateClientUseCase createClientUseCase;
    @MockBean IFindClientUseCase findClientUseCase;
    @MockBean IUpdateClientUseCase updateClientUseCase;
    @MockBean IDeleteClientUseCase deleteClientUseCase;
    @MockBean IGetClientStatsUseCase getClientStatsUseCase;
    @MockBean IFindClientsWithDerivedUseCase listClientsWithDerivedUseCase;
    @MockBean ClientEventPublisher clientEventPublisher;

    private String updateJson() throws Exception {
        Map<String, Object> p = new HashMap<>();
        p.put("firstName", "Ana");
        p.put("lastName", "Pérez");
        p.put("age", 33);
        p.put("dateOfBirth", "1992-02-02");

        return om.writeValueAsString(p);
    }

    private static ArgumentMatcher<Client> hasUpdateData(
            long id, String fn, String ln, Integer age, LocalDate dob, Long userId /* puede ser null */) {
        return c -> c != null
                && Objects.equals(c.getId(), id)
                && Objects.equals(c.getFirstName(), fn)
                && Objects.equals(c.getLastName(), ln)
                && Objects.equals(c.getAge(), age)
                && Objects.equals(c.getDateOfBirth(), dob)
                && Objects.equals(c.getUserId(), userId);
    }

    @Test
    void update_ok_returns200() throws Exception {
        String json = updateJson();

        var updated = Client.builder()
                .id(123L)
                .firstName("Ana")
                .lastName("Pérez")
                .age(33)
                .dateOfBirth(LocalDate.parse("1992-02-02"))
                .userId(null)
                .companyId(2L)
                .build();

        when(updateClientUseCase.execute(argThat(
                hasUpdateData(123L, "Ana", "Pérez", 33, LocalDate.parse("1992-02-02"), null)
        ))).thenReturn(updated);

        mvc.perform(put("/clients/update/{id}", 123L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(123))
                .andExpect(jsonPath("$.firstName").value("Ana"))
                .andExpect(jsonPath("$.lastName").value("Pérez"));

        verify(updateClientUseCase, times(1)).execute(any());
    }

    @Test
    void update_badRequest_whenValidationFails() throws Exception {
        String json = updateJson();

        // simulamos error de validación -> 400
        when(updateClientUseCase.execute(any()))
                .thenThrow(new BadRequestExceptionService("Los datos del cliente no son válidos"));

        mvc.perform(put("/clients/update/{id}", 77L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string(
                        org.hamcrest.Matchers.containsString("Los datos del cliente no son válidos")
                ));
    }

    @Test
    void update_notFound_whenClientDoesNotExist() throws Exception {
        String json = updateJson();

        // simulamos no encontrado -> 404 con GenericMessages.RESOURCE_NOT_EXIST
        when(updateClientUseCase.execute(any()))
                .thenThrow(new NotFoundExceptionService(GenericMessages.RESOURCE_NOT_EXIST));

        mvc.perform(put("/clients/update/{id}", 9999L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andExpect(content().string(GenericMessages.RESOURCE_NOT_EXIST));
    }
}

package trackapp.icube04backend.infrastructure.adapters.client_module.restcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import trackapp.icube04backend.common.exceptions.GenericMessages;
import trackapp.icube04backend.common.exceptions.NotFoundExceptionService;
import trackapp.icube04backend.common.messaging.publishers.ClientEventPublisher;
import trackapp.icube04backend.infrastructure.configuration.security.jwt.filters.JwtFilter;
import trackapp.icube04backend.modules.client_module.domain.ports.usecases.*;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ClientRestController.class)
@AutoConfigureMockMvc(addFilters = false)
class ClientRestControllerDeleteTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;

    // mock de jwtFilter
    @MockBean JwtFilter jwtFilter;

    // Dependencias del controller
    @MockBean ICreateClientUseCase createClientUseCase;
    @MockBean IFindClientUseCase findClientUseCase;
    @MockBean IUpdateClientUseCase updateClientUseCase;
    @MockBean IDeleteClientUseCase deleteClientUseCase;
    @MockBean IGetClientStatsUseCase getClientStatsUseCase;
    @MockBean IFindClientsWithDerivedUseCase listClientsWithDerivedUseCase;
    @MockBean ClientEventPublisher clientEventPublisher;

    @Test
    void delete_ok_returns204() throws Exception {
        long id = 123L;

        doNothing().when(deleteClientUseCase).execute(id);

        mvc.perform(delete("/clients/{id}", id)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(deleteClientUseCase, times(1)).execute(id);
        verifyNoMoreInteractions(deleteClientUseCase);
    }

    @Test
    void delete_notFound_returns404() throws Exception {
        long id = 9999L;

        doThrow(new NotFoundExceptionService(GenericMessages.RESOURCE_NOT_EXIST))
                .when(deleteClientUseCase).execute(id);

        mvc.perform(delete("/clients/{id}", id)
                        .with(csrf()))
                .andExpect(status().isNotFound())
                .andExpect(content().string(GenericMessages.RESOURCE_NOT_EXIST));

        verify(deleteClientUseCase, times(1)).execute(id);
        verifyNoMoreInteractions(deleteClientUseCase);
    }

}

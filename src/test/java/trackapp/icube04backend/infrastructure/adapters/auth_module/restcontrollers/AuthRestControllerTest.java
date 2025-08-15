package trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import trackapp.icube04backend.infrastructure.configuration.security.dtos.AuthRequest;
import trackapp.icube04backend.infrastructure.configuration.security.dtos.AuthResponse;
import trackapp.icube04backend.infrastructure.configuration.security.jwt.JwtUtil;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.IBuildAuthResponseUseCase;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.IForgotPasswordByUsernameUseCase;

import static org.mockito.ArgumentMatchers.argThat;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// Desactivamos filtros de seguridad para enfocarnos en el controller
@WebMvcTest(controllers = AuthRestController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthRestControllerTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;

    // Dependencias del controller:
    @MockBean IForgotPasswordByUsernameUseCase forgotPasswordByUsernameUseCase;
    @MockBean AuthenticationManager authenticationManager;
    @MockBean JwtUtil jwtUtil;
    @MockBean IBuildAuthResponseUseCase buildAuthResponseUseCase;
    @MockBean org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;




    @Test
    void login_ok_devuelveAuthResponse() throws Exception {
        // given
        var req = new AuthRequest("somasAdmin", "Sec-Adm-So");

        // Authentication con una authority cualquiera (puede ser la real)
        var authOk = new UsernamePasswordAuthenticationToken(
                "somasAdmin",
                null,
                java.util.List.of(new org.springframework.security.core.authority.SimpleGrantedAuthority("SOMAS_SUPER_ADMIN"))
        );

        // El manager devuelve el Authentication cuando user/pass son correctos
        when(authenticationManager.authenticate(
                argThat(a -> a instanceof UsernamePasswordAuthenticationToken t
                        && "somasAdmin".equals(t.getName())
                        && "Sec-Adm-So".equals(t.getCredentials()))
        )).thenReturn(authOk);

        // Para cualquier otra combinación: inválido
        when(authenticationManager.authenticate(
                argThat(a -> a instanceof UsernamePasswordAuthenticationToken t
                        && (!"somasAdmin".equals(t.getName()) || !"Sec-Adm-So".equals(t.getCredentials())))
        )).thenThrow(new org.springframework.security.core.AuthenticationException("bad") {});

        // Nuevo stub: firma (username, authorities)
        when(jwtUtil.generateToken(eq("somasAdmin"), anyCollection()))
                .thenReturn("fake.jwt");

        var expected = new AuthResponse(
                2L,
                "fake.jwt",
                "somasAdmin",
                "usuariosuperadmin@gmail.com",
                java.util.List.of(), // ajusta si corresponde
                1L,
                java.util.List.of()
        );
        when(buildAuthResponseUseCase.build("fake.jwt", "somasAdmin")).thenReturn(expected);

        // when/then
        mvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").value("fake.jwt"))
                .andExpect(jsonPath("$.username").value("somasAdmin"));
    }

    @Test
    void login_credencialesInvalidas_devuelve401() throws Exception {
        var req = new AuthRequest("wrong", "bad");

        when(authenticationManager.authenticate(any()))
                .thenThrow(new org.springframework.security.core.AuthenticationException("bad") {});

        mvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(req)))
                .andExpect(status().isUnauthorized());
    }
}

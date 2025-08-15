package trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import trackapp.icube04backend.common.exceptions.ExceptionDetail;
import trackapp.icube04backend.common.exceptions.UnauthorizedDetailExceptionService;
import trackapp.icube04backend.infrastructure.configuration.security.dtos.AuthResponse;
import trackapp.icube04backend.infrastructure.configuration.security.jwt.JwtUtil;
import trackapp.icube04backend.infrastructure.configuration.security.dtos.AuthRequest;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.IBuildAuthResponseUseCase;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.IForgotPasswordByUsernameUseCase;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthRestController {

    private final IForgotPasswordByUsernameUseCase forgotPasswordByUsernameUseCase;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final IBuildAuthResponseUseCase buildAuthResponseUseCase;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public AuthResponse tryLogin(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.username(),
                            authRequest.password()
                    )
            );

            // (opcional) poblar el contexto si después lo vas a usar
            SecurityContextHolder.getContext().setAuthentication(authentication);

            var authorities = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            String token = jwtUtil.generateToken(authRequest.username(), authorities);

            return buildAuthResponseUseCase.build(token, authRequest.username());

        } catch (AuthenticationException e) {
            ExceptionDetail detail = new ExceptionDetail("Usuario y/o Password incorrectos");
            //detail.addDetail("username", "invalid");
            //detail.addDetail("password", "invalid");
            throw new UnauthorizedDetailExceptionService(detail);
        }
    }



    /*@PostMapping("/prod/login")
    public AuthProdResponse loginProd(@RequestBody AuthProdRequest authRequest) {
        ProdUserEntity user = prodUserAdapter.findByEmail(authRequest.email());
        if (user == null) {
            ExceptionDetail detail = new ExceptionDetail("Usuario y/o Password incorrectos");
            throw new UnauthorizedDetailExceptionService(detail);
        }
        String token = jwtUtil.generateToken(authRequest.email());
        return buildAuthResponseUseCase.buildProd(token, authRequest.email());
    }*/










    @PostMapping("forgot-password")
    public ResponseEntity<Void> resetPassword(@RequestParam String username) {
        forgotPasswordByUsernameUseCase.execute(username);
        return ResponseEntity.noContent().build();
    }

}

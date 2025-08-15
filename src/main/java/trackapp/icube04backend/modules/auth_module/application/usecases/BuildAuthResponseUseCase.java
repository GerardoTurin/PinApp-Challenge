package trackapp.icube04backend.modules.auth_module.application.usecases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.responses.RoleResponse;
import trackapp.icube04backend.infrastructure.adapters.company_module.restcontrollers.dtos.responses.CompanyResponse;
import trackapp.icube04backend.infrastructure.configuration.security.dtos.AuthResponse;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IUserRepository;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.IBuildAuthResponseUseCase;

@UseCase
@RequiredArgsConstructor
public class BuildAuthResponseUseCase implements IBuildAuthResponseUseCase {

    private final IUserRepository userRepository;

    @Override
    public AuthResponse build(String jwt, String usernameOrEmail) {
        var user = userRepository.findByUsername(usernameOrEmail);

        if (user == null) {
            user = userRepository.findByEmail(usernameOrEmail);
        }

        return AuthResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .userRoles(user.getRoles().stream().map(RoleResponse::convertFromDomain).toList())
                .userTypeId(user.getUserType().getId())
                .companies(user.getCompanies().stream().map(CompanyResponse::convertFromDomain).toList())
                .jwt(jwt)
                .build();
    }
}

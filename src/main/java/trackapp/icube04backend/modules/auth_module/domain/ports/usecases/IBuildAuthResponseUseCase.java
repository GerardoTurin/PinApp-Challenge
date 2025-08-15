package trackapp.icube04backend.modules.auth_module.domain.ports.usecases;

import trackapp.icube04backend.infrastructure.configuration.security.dtos.AuthProdResponse;
import trackapp.icube04backend.infrastructure.configuration.security.dtos.AuthResponse;

public interface IBuildAuthResponseUseCase {

    AuthResponse build(String jwt, String username);

}

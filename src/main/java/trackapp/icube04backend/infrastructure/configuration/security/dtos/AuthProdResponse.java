package trackapp.icube04backend.infrastructure.configuration.security.dtos;

import lombok.Builder;
import trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.responses.RoleResponse;
import trackapp.icube04backend.infrastructure.adapters.company_module.restcontrollers.dtos.responses.CompanyResponse;

import java.util.List;

@Builder
public record AuthProdResponse(
         Long id,
         String jwt,
         String name,
         String email
) {
}

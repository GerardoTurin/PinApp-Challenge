package trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.responses;

import lombok.Builder;
import trackapp.icube04backend.infrastructure.adapters.company_module.restcontrollers.dtos.responses.CompanyResponse;
import trackapp.icube04backend.modules.auth_module.domain.models.Role;

@Builder
public record RoleResponse(
        Long id,
        String name,
        CompanyResponse company
) {

    public static RoleResponse convertFromDomain(Role role){
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .company(CompanyResponse.convertFromDomain(role.getCompany()))
                .build();
    }
}

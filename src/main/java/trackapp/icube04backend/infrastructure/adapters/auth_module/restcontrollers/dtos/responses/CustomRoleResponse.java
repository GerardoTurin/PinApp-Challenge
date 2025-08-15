package trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.responses;

import lombok.Builder;
import trackapp.icube04backend.modules.auth_module.domain.models.Role;

@Builder
public record CustomRoleResponse(
        Long id,
        String name
) {

    public static CustomRoleResponse convertFromDomain(Role role){
        return CustomRoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}

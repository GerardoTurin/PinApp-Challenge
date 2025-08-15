package trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.responses;

import lombok.Builder;

@Builder
public record CustomModulesRolesPermissionsResponse(
        Long moduleId,
        Long permissionId,
        Long menuItemId
) {
}

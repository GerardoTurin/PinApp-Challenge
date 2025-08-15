package trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.responses;

import lombok.Builder;

import java.util.List;

@Builder
public record CustomRoleResponse2(
        Long id,
        String name,
        List<CustomModulesRolesPermissionsResponse> modulePermissions
) {
}

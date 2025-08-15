package trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.requests;

import java.util.List;

public record CustomRoleUpdateRequest(
        Long id,
        String name,
        List<CustomModulesRolesPermissionsUpdateRequest> modulePermissions
) {
}

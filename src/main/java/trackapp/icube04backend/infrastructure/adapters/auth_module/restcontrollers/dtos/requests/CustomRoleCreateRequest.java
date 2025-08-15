package trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.requests;

import java.util.List;

public record CustomRoleCreateRequest(
        String name,
        List<CustomModulesRolesPermissionsCreateRequest> modulePermissions
) {
}

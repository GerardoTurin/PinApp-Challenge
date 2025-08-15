package trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.requests;

public record CustomModulesRolesPermissionsUpdateRequest(
        Long id,
        Long moduleId,
        Long permissionId,
        Long menuItemId
) {
}

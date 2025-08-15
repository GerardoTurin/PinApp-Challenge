package trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.requests;


public record CustomModulesRolesPermissionsCreateRequest(
        Long moduleId,
        Long permissionId,
        Long menuItemId
) {
}

package trackapp.icube04backend.modules.auth_module.domain.ports.usecases;

import trackapp.icube04backend.modules.auth_module.domain.models.PermissionType;

public interface IUpdatePermissionTypeUseCase {

    PermissionType update(PermissionType permissionType);
}

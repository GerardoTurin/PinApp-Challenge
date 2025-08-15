package trackapp.icube04backend.modules.auth_module.application.usecases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.modules.auth_module.domain.models.PermissionType;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.IUpdatePermissionTypeUseCase;


@UseCase
@RequiredArgsConstructor
public class UpdatePermissionTypeUseCase implements IUpdatePermissionTypeUseCase {



    @Override
    public PermissionType update(PermissionType permissionType) {
        return null;
    }
}

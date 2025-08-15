package trackapp.icube04backend.modules.auth_module.application.usecases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.modules.auth_module.domain.models.PermissionType;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IModulesRolesPermissionsRepository;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IPermissionTypeRepository;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.IFindAllPermissionTypesUseCase;

import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindAllPermissionTypesUseCase implements IFindAllPermissionTypesUseCase {

    private final IPermissionTypeRepository permissionTypeRepository;
    private final IModulesRolesPermissionsRepository modulesRolesPermissionsRepository;

    @Override
    public List<PermissionType> findAll() {
        return permissionTypeRepository.findAll();
    }

    @Override
    public List<PermissionType> findByRoleId(Long id) {
        var mrps = modulesRolesPermissionsRepository.findByRoleId(id);

        var res = new ArrayList<PermissionType>();

        mrps.forEach(mrp -> {
            if(mrp.getRole().getId().equals(id)){
                res.add(mrp.getPermissionType());
            }
        });

        return res;
    }
}

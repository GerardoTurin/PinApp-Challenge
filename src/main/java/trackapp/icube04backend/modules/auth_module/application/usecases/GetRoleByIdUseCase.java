package trackapp.icube04backend.modules.auth_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.responses.CustomModulesRolesPermissionsResponse;
import trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.responses.CustomRoleResponse2;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IModulesRolesPermissionsRepository;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.IGetRoleByIdUseCase;
import trackapp.icube04backend.modules.catalog_module.domain.ports.repositories.IRoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRoleByIdUseCase implements IGetRoleByIdUseCase {

    private final IRoleRepository roleRepository;
    private final IModulesRolesPermissionsRepository modulesRolesPermissionsRepository;

    @Override
    public CustomRoleResponse2 getRoleById(Long id) {
        var role = roleRepository.findById(id);
        var mrps = modulesRolesPermissionsRepository.findByRoleId(id);

        List<CustomModulesRolesPermissionsResponse> lista = new ArrayList<>();

        mrps.forEach(mrp -> {
            var tmp = CustomModulesRolesPermissionsResponse.builder()
                    .moduleId(mrp.getModuleApp().getId())
                    .permissionId(mrp.getPermissionType().getId())
                    .menuItemId(mrp.getMenuItem() != null ? mrp.getMenuItem().getId() : null)
                    .build();

            lista.add(tmp);
        });

        return CustomRoleResponse2.builder()
                .id(id)
                .name(role.getName())
                .modulePermissions(lista)
                .build();

    }
}

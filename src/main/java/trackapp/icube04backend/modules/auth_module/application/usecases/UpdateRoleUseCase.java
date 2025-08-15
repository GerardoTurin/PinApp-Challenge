package trackapp.icube04backend.modules.auth_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.requests.CustomRoleUpdateRequest;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.auth_module.domain.models.ModulesRolesPermissions;
import trackapp.icube04backend.modules.auth_module.domain.models.Role;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IModulesRolesPermissionsRepository;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IPermissionTypeRepository;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.IUpdateRoleUseCase;
import trackapp.icube04backend.modules.catalog_module.domain.ports.repositories.IMenuItemRepository;
import trackapp.icube04backend.modules.catalog_module.domain.ports.repositories.IModuleAppRepository;
import trackapp.icube04backend.modules.catalog_module.domain.ports.repositories.IRoleRepository;
import trackapp.icube04backend.modules.company_module.domain.ports.repositories.ICompanyRepository;

@Service
@RequiredArgsConstructor
public class UpdateRoleUseCase implements IUpdateRoleUseCase {

    private final IRoleRepository roleRepository;
    private final IModuleAppRepository moduleAppRepository;
    private final IPermissionTypeRepository permissionTypeRepository;
    private final IModulesRolesPermissionsRepository modulesRolesPermissionsRepository;
    private final ICompanyRepository companyRepository;
    private final ISessionService sessionService;
    private final IMenuItemRepository menuItemRepository;

    @Override
    public Role update(CustomRoleUpdateRequest request) {
        var oldRole = roleRepository.findById(request.id());

        oldRole = roleRepository.save(Role.builder()
                .id(request.id())
                .name(request.name())
                .company(oldRole.getCompany())
                .build());


        Role finalOldRole = oldRole;
        var oldMp = modulesRolesPermissionsRepository.findByRoleId(finalOldRole.getId());
        modulesRolesPermissionsRepository.deleteByRoleId(request.id());

        request.modulePermissions().forEach(mp -> {
            var module = moduleAppRepository.findById(mp.moduleId());
            var permiso = permissionTypeRepository.findById(mp.permissionId());
            var menuItem = mp.menuItemId() != null ? menuItemRepository.findById(mp.menuItemId()) : null;

            var mrp = ModulesRolesPermissions.builder()
                    .role(finalOldRole)
                    .permissionType(permiso)
                    .moduleApp(module)
                    .menuItem(menuItem)
                    .build();

            modulesRolesPermissionsRepository.save(mrp);
        });

        return oldRole;
    }
}

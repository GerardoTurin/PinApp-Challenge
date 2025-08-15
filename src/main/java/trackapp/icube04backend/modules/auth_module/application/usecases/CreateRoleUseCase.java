package trackapp.icube04backend.modules.auth_module.application.usecases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.requests.CustomRoleCreateRequest;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.auth_module.domain.models.ModulesRolesPermissions;
import trackapp.icube04backend.modules.auth_module.domain.models.Role;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IModulesRolesPermissionsRepository;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IPermissionTypeRepository;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.ICreateRoleUseCase;
import trackapp.icube04backend.modules.catalog_module.domain.ports.repositories.IMenuItemRepository;
import trackapp.icube04backend.modules.catalog_module.domain.ports.repositories.IModuleAppRepository;
import trackapp.icube04backend.modules.catalog_module.domain.ports.repositories.IRoleRepository;
import trackapp.icube04backend.modules.company_module.domain.ports.repositories.ICompanyRepository;


@UseCase
@RequiredArgsConstructor
public class CreateRoleUseCase implements ICreateRoleUseCase {

    private final IRoleRepository roleRepository;
    private final IModuleAppRepository moduleAppRepository;
    private final IPermissionTypeRepository permissionTypeRepository;
    private final IModulesRolesPermissionsRepository modulesRolesPermissionsRepository;
    private final ICompanyRepository companyRepository;
    private final ISessionService sessionService;
    private final IMenuItemRepository menuItemRepository;

    @Override
    public Role create(CustomRoleCreateRequest request) {

        var companyRole = companyRepository.findById(sessionService.getCompanyId());

        var newRole = roleRepository.save(Role.builder()
                .name(request.name())
                .company(companyRole)
                .build());


        request.modulePermissions().forEach(mp -> {
        var module = moduleAppRepository.findById(mp.moduleId());
        var permiso = permissionTypeRepository.findById(mp.permissionId());
        var menuItem = mp.menuItemId() != null ? menuItemRepository.findById(mp.menuItemId()) : null;

        var mrp = ModulesRolesPermissions.builder()
                .role(newRole)
                .permissionType(permiso)
                .moduleApp(module)
                .menuItem(menuItem)
                .build();

            modulesRolesPermissionsRepository.save(mrp);
        });

        return newRole;
    }
}

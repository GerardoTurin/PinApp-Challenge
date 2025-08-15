package trackapp.icube04backend.modules.auth_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IModulesRolesPermissionsRepository;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.IDeleteRoleByIdUseCase;
import trackapp.icube04backend.modules.catalog_module.domain.ports.repositories.IRoleRepository;

@Service
@RequiredArgsConstructor
public class DeleteRoleByIdUseCase implements IDeleteRoleByIdUseCase {

    private final IRoleRepository roleRepository;
    private final IModulesRolesPermissionsRepository modulesRolesPermissionsRepository;

    @Override
    public void delete(Long id) {
        modulesRolesPermissionsRepository.deleteByRoleId(id);
        roleRepository.deleteById(id);
    }
}

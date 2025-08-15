package trackapp.icube04backend.modules.auth_module.domain.ports.repositories;

import trackapp.icube04backend.modules.auth_module.domain.models.ModulesRolesPermissions;

import java.util.List;

public interface IModulesRolesPermissionsRepository {
    void save(ModulesRolesPermissions mrp);

    List<ModulesRolesPermissions> findByRoleId(Long id);

    void deleteById(Long id);

    void deleteByRoleId(Long roleId);
}

package trackapp.icube04backend.modules.catalog_module.domain.ports.repositories;


import trackapp.icube04backend.modules.auth_module.domain.models.Role;

import java.util.List;


public interface IRoleRepository {
    Role save(Role role);

    List<Role> findAll();

    Role findById(Long id);

    void deleteById(Long id);
}

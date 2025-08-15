package trackapp.icube04backend.modules.auth_module.domain.ports.repositories;

import trackapp.icube04backend.modules.auth_module.domain.models.PermissionType;

import java.util.List;

public interface IPermissionTypeRepository {

    List<PermissionType> findAll();

    PermissionType findById(Long id);
}

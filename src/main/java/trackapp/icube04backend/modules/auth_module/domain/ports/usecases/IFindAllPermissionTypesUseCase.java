package trackapp.icube04backend.modules.auth_module.domain.ports.usecases;

import trackapp.icube04backend.modules.auth_module.domain.models.PermissionType;

import java.util.List;

public interface IFindAllPermissionTypesUseCase {

    List<PermissionType> findAll();

    List<PermissionType> findByRoleId(Long id);
}

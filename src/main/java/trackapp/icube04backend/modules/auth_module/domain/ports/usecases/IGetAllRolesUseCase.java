package trackapp.icube04backend.modules.auth_module.domain.ports.usecases;

import trackapp.icube04backend.modules.auth_module.domain.models.Role;

import java.util.List;

public interface IGetAllRolesUseCase {

    List<Role> getAllByCompanyId();
}

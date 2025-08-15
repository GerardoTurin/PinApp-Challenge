package trackapp.icube04backend.modules.auth_module.domain.ports.usecases;

import trackapp.icube04backend.modules.auth_module.domain.models.User;

import java.util.List;

public interface IFindAllUsersUseCase {

    List<User> findAllByCompanyId();
}

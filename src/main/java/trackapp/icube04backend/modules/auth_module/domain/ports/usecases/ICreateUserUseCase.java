package trackapp.icube04backend.modules.auth_module.domain.ports.usecases;

import trackapp.icube04backend.modules.auth_module.domain.models.User;

public interface ICreateUserUseCase {

    User create(User user  , byte[] img);
}

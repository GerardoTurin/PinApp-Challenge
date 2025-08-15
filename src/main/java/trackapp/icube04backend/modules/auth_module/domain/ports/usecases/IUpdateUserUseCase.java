package trackapp.icube04backend.modules.auth_module.domain.ports.usecases;

import trackapp.icube04backend.modules.auth_module.domain.models.User;

public interface IUpdateUserUseCase {
    User update(User user , byte[] img);
}

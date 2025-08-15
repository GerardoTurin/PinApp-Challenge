package trackapp.icube04backend.modules.track_module.domain.ports.usescases;

import trackapp.icube04backend.modules.track_module.domain.models.UserGroup;

public interface ICreateUserGroupUseCase {
    UserGroup execute(UserGroup userGroup);
}

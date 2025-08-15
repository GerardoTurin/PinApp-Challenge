package trackapp.icube04backend.modules.track_module.domain.ports.usescases;

import trackapp.icube04backend.modules.track_module.domain.models.UserGroup;

public interface IUpdateUserGroupUseCase {
    UserGroup execute(UserGroup userGroup);
}

package trackapp.icube04backend.modules.track_module.domain.ports.usescases;

import trackapp.icube04backend.modules.track_module.domain.models.UserGroup;

import java.util.List;

public interface IFindUserGroupUseCase {
    UserGroup findById(Long id);

    List<UserGroup> findAllByCompanyIdAndByUserId(Long userId);
}

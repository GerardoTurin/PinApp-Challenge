package trackapp.icube04backend.modules.track_module.domain.ports.repository;

import trackapp.icube04backend.modules.track_module.domain.models.UserGroup;

import java.util.List;

public interface IUserGroupRepository {
    UserGroup save(UserGroup userGroup);

    UserGroup findById(Long id);

    List<UserGroup> findAllByCompanyIdAndByUserId(Long companyId, Long userId);

    void delete(UserGroup userGroup);
}

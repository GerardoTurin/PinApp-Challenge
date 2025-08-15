package trackapp.icube04backend.modules.track_module.domain.ports.repository;

import trackapp.icube04backend.modules.track_module.domain.models.Group;

import java.util.List;

public interface IGroupRepository {
    Group save(Group group);

    Group findById(Long id);

    List<Group> findAllByCompanyId(Long companyId);

    List<Group> findAllByCompanyIdAndByUserId(Long companyId, Long userId);

    void delete(Group group);
}

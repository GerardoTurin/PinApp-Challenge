package trackapp.icube04backend.infrastructure.adapters.track_module.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import trackapp.icube04backend.infrastructure.db.jpa.GroupJPARepository;
import trackapp.icube04backend.infrastructure.db.model.GroupEntity;
import trackapp.icube04backend.modules.track_module.domain.models.Group;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IGroupRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GroupRepository implements IGroupRepository {

    private final GroupJPARepository jpaRepository;

    @Override
    public Group save(Group group) {

        GroupEntity groupEntity = GroupEntity.convertFromDomain(group);
        return jpaRepository.save(groupEntity).convertToDomain();
    }

    @Override
    public Group findById(Long id) {

        return jpaRepository.findById(id)
                .map(GroupEntity::convertToDomain)
                .orElse(null);
    }

    @Override
    public List<Group> findAllByCompanyId(Long companyId) {

        return jpaRepository.findAllByCompanyId(companyId).stream()
                .map(GroupEntity::convertToDomain)
                .toList();
    }

    @Override
    public List<Group> findAllByCompanyIdAndByUserId(Long companyId, Long userId) {

        return jpaRepository.findAllByCompanyIdAndByUserId(companyId, userId).stream()
                .map(GroupEntity::convertToDomain)
                .toList();
    }


    @Override
    public void delete(Group group) {
        jpaRepository.delete(GroupEntity.convertFromDomain(group));
    }
}

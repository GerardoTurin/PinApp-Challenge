package trackapp.icube04backend.infrastructure.adapters.track_module.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import trackapp.icube04backend.infrastructure.db.jpa.UserGroupJPARepository;
import trackapp.icube04backend.infrastructure.db.model.UserGroupEntity;
import trackapp.icube04backend.modules.track_module.domain.models.UserGroup;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IUserGroupRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserGroupRepository implements IUserGroupRepository {

    private final UserGroupJPARepository jpaRepository;

    @Override
    public UserGroup save(UserGroup userGroup) {

        UserGroupEntity userGroupEntity = UserGroupEntity.convertFromDomain(userGroup);
        return jpaRepository.save(userGroupEntity).convertToDomain();
    }

    @Override
    public UserGroup findById(Long id) {

        return jpaRepository.findById(id)
                .map(UserGroupEntity::convertToDomain)
                .orElse(null);
    }

    @Override
    public List<UserGroup> findAllByCompanyIdAndByUserId(Long companyId, Long userId) {

        return jpaRepository.findAllByCompanyIdAndByUserId(companyId, userId)
                .stream()
                .map(UserGroupEntity::convertToDomain)
                .toList();
    }

    @Override
    public void delete(UserGroup userGroup) {
        jpaRepository.delete(UserGroupEntity.convertFromDomain(userGroup));
    }
}

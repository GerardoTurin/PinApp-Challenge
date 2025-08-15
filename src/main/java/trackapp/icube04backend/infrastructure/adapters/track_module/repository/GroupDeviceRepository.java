package trackapp.icube04backend.infrastructure.adapters.track_module.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import trackapp.icube04backend.infrastructure.db.jpa.GroupDeviceJPARepository;
import trackapp.icube04backend.infrastructure.db.model.GroupDeviceEntity;
import trackapp.icube04backend.modules.track_module.domain.models.GroupDevice;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IGroupDeviceRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GroupDeviceRepository implements IGroupDeviceRepository {

    private final GroupDeviceJPARepository jpaRepository;

    @Override
    public GroupDevice save(GroupDevice groupDevice) {

        GroupDeviceEntity groupDeviceEntity = GroupDeviceEntity.convertFromDomain(groupDevice);
        return jpaRepository.save(groupDeviceEntity).convertToDomain();
    }

    @Override
    public GroupDevice findById(Long id) {

        return jpaRepository.findById(id)
                .map(GroupDeviceEntity::convertToDomain)
                .orElse(null);
    }

    @Override
    public List<GroupDevice> findAllByCompanyIdAndByUserId(Long companyId, Long userId) {

        return jpaRepository.findAllByCompanyIdAndByUserId(companyId, userId)
                .stream()
                .map(GroupDeviceEntity::convertToDomain)
                .toList();
    }

    @Override
    public void delete(GroupDevice groupDevice) {
        jpaRepository.delete(GroupDeviceEntity.convertFromDomain(groupDevice));
    }
}

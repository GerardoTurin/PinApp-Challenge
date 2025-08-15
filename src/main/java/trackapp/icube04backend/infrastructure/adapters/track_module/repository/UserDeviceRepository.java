package trackapp.icube04backend.infrastructure.adapters.track_module.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import trackapp.icube04backend.infrastructure.db.jpa.UserDeviceJPARepository;
import trackapp.icube04backend.infrastructure.db.model.UserDeviceEntity;
import trackapp.icube04backend.modules.track_module.domain.models.UserDevice;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IUserDeviceRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDeviceRepository implements IUserDeviceRepository {

    private final UserDeviceJPARepository jpaRepository;

    @Override
    public UserDevice save(UserDevice userDevice) {

        UserDeviceEntity userDeviceEntity = UserDeviceEntity.convertFromDomain(userDevice);
        return jpaRepository.save(userDeviceEntity).convertToDomain();
    }

    @Override
    public UserDevice findById(Long id) {
        return jpaRepository.findById(id)
                .map(UserDeviceEntity::convertToDomain)
                .orElse(null);
    }

    @Override
    public List<UserDevice> findAllByCompanyIdAndByUserId(Long companyId, Long userId) {
        return jpaRepository.findAllByCompanyIdAndByUserId(companyId, userId)
                .stream()
                .map(UserDeviceEntity::convertToDomain)
                .toList();
    }

    @Override
    public void delete(UserDevice userDevice) {
        jpaRepository.delete(UserDeviceEntity.convertFromDomain(userDevice));
    }
}

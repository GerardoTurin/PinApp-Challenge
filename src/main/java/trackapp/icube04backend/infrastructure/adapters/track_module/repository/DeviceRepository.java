package trackapp.icube04backend.infrastructure.adapters.track_module.repository;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import trackapp.icube04backend.infrastructure.db.jpa.DeviceJPARepository;
import trackapp.icube04backend.infrastructure.db.model.DeviceEntity;
import trackapp.icube04backend.modules.track_module.domain.models.Device;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IDeviceRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DeviceRepository implements IDeviceRepository {

    private final DeviceJPARepository jpaRepository;

    @Transactional
    @Override
    public Device save(Device device) {

        DeviceEntity deviceEntity = DeviceEntity.convertFromDomain(device);
        return jpaRepository.save(deviceEntity).convertToDomain();
    }

    @Override
    public Device findById(Long id) {

        return jpaRepository.findById(id)
                .map(DeviceEntity::convertToDomain)
                .orElse(null);
    }

    @Override
    public List<Device> findAllByCompanyId(Long companyId) {
        return List.of();
    }

    @Override
    public List<Device> findAllByCompanyIdAndByUserId(Long companyId, Long userId) {
        return jpaRepository.findAllByCompanyIdAndByUserId(companyId, userId).stream()
                .map(DeviceEntity::convertToDomain)
                .toList();
    }

    @Override
    public List<Device> findAllByUserId(Long userId) {
        return jpaRepository.findAllByUserId(userId).stream()
                .map(DeviceEntity::convertToDomain)
                .toList();
    }

    @Transactional
    @Override
    public void delete(Device device) {

        jpaRepository.delete(DeviceEntity.convertFromDomain(device));
    }
}

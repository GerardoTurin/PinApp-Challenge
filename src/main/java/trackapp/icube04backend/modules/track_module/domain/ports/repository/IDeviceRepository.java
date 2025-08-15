package trackapp.icube04backend.modules.track_module.domain.ports.repository;

import trackapp.icube04backend.modules.track_module.domain.models.Device;

import java.util.List;

public interface IDeviceRepository {

    Device save(Device device);

    Device findById(Long id);

    List<Device> findAllByCompanyId(Long companyId);

    List<Device> findAllByCompanyIdAndByUserId(Long companyId, Long userId);

    List<Device> findAllByUserId(Long userId);

    void delete(Device device);
}

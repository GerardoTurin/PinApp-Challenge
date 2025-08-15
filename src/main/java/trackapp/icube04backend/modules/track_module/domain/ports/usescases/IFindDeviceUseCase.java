package trackapp.icube04backend.modules.track_module.domain.ports.usescases;

import trackapp.icube04backend.modules.track_module.domain.models.Device;

import java.util.List;

public interface IFindDeviceUseCase {

    Device findById(Long id);

    List<Device> findAllByCompanyId(Long companyId);

    List<Device> findAllByCompanyIdAndByUserId(Long userId);

    List<Device> findAllByUserId(Long userId);
}

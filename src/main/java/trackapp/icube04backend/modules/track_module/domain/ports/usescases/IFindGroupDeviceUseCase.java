package trackapp.icube04backend.modules.track_module.domain.ports.usescases;

import trackapp.icube04backend.modules.track_module.domain.models.GroupDevice;

import java.util.List;

public interface IFindGroupDeviceUseCase {

    GroupDevice findById(Long id);

    List<GroupDevice> findAllByCompanyIdAndByUserId(Long userId);
}

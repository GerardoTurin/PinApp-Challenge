package trackapp.icube04backend.modules.track_module.domain.ports.usescases;

import trackapp.icube04backend.modules.track_module.domain.models.UserDevice;

import java.util.List;

public interface IFindUserDeviceUseCase {

    UserDevice findById(Long id);

    List<UserDevice> findAllByCompanyIdAndByUserId(Long userId);
}

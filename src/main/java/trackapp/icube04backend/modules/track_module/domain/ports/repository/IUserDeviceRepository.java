package trackapp.icube04backend.modules.track_module.domain.ports.repository;

import trackapp.icube04backend.modules.track_module.domain.models.UserDevice;

import java.util.List;

public interface IUserDeviceRepository {
    UserDevice save(UserDevice userDevice);

    UserDevice findById(Long id);

    List<UserDevice> findAllByCompanyIdAndByUserId(Long companyId, Long userId);

    void delete(UserDevice userDevice);
}

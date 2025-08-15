package trackapp.icube04backend.modules.track_module.domain.ports.repository;

import trackapp.icube04backend.modules.track_module.domain.models.GroupDevice;

import java.util.List;

public interface IGroupDeviceRepository {
    GroupDevice save(GroupDevice groupDevice);

    GroupDevice findById(Long id);

    List<GroupDevice> findAllByCompanyIdAndByUserId(Long companyId, Long userId);

    void delete(GroupDevice groupDevice);

}

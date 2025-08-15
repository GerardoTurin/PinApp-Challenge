package trackapp.icube04backend.modules.track_module.domain.ports.usescases;

import trackapp.icube04backend.modules.track_module.domain.models.GroupDevice;

public interface ICreateGroupDeviceUseCase {
    GroupDevice execute(GroupDevice groupDevice);
}

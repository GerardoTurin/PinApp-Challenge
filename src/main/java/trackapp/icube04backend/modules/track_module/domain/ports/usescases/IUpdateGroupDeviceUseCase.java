package trackapp.icube04backend.modules.track_module.domain.ports.usescases;

import trackapp.icube04backend.modules.track_module.domain.models.GroupDevice;

public interface IUpdateGroupDeviceUseCase {
    GroupDevice execute(GroupDevice groupDevice);
}

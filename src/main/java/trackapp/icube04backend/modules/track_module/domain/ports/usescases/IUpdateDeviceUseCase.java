package trackapp.icube04backend.modules.track_module.domain.ports.usescases;

import trackapp.icube04backend.modules.track_module.domain.models.Device;

public interface IUpdateDeviceUseCase {
    Device execute(Device device);
}

package trackapp.icube04backend.modules.track_module.domain.ports.usescases;


import trackapp.icube04backend.modules.track_module.domain.models.Device;

public interface ICreateDeviceUseCase {
    Device execute(Device device);
}

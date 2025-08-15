package trackapp.icube04backend.modules.track_module.domain.ports.usescases;

import trackapp.icube04backend.modules.track_module.domain.models.UserDevice;

public interface ICreateUserDeviceUseCase {
    UserDevice execute(UserDevice userDevice);
}

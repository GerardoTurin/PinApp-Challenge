package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos;

import trackapp.icube04backend.modules.auth_module.domain.models.User;
import trackapp.icube04backend.modules.track_module.domain.models.Device;
import trackapp.icube04backend.modules.track_module.domain.models.UserDevice;

public record UserDeviceCreateRequest(
        Long userId,
        Long deviceId,
        Long createdUserId
        //Long statusConfigDetailId
) {

    public UserDevice convertToDomain() {
        return UserDevice.builder()
                .user(User.builder().id(this.userId).build())
                .device(Device.builder().id(this.deviceId).build())
                .createdUser(User.builder().id(this.createdUserId).build())
                //.statusConfigDetail(ConfigDetail.builder().id(this.statusConfigDetailId).build())
                .build();
    }
}

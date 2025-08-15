package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos;

import trackapp.icube04backend.modules.auth_module.domain.models.User;
import trackapp.icube04backend.modules.track_module.domain.models.Device;
import trackapp.icube04backend.modules.track_module.domain.models.Group;
import trackapp.icube04backend.modules.track_module.domain.models.GroupDevice;

public record GroupDeviceCreateRequest(
        Long groupId,
        Long deviceId,
        Long createdUserId
) {

    public GroupDevice convertToDomain() {
        return GroupDevice.builder()
                .group(Group.builder().id(this.groupId).build())
                .device(Device.builder().id(this.deviceId).build())
                .createdUser(User.builder().id(this.createdUserId).build())
                .build();
    }
}

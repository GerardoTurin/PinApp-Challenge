package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos;

import trackapp.icube04backend.modules.track_module.domain.models.Device;
import trackapp.icube04backend.modules.track_module.domain.models.Group;
import trackapp.icube04backend.modules.track_module.domain.models.GroupDevice;

public record GroupDeviceUpdateRequest(
        Long id,
        Long groupId,
        Long deviceId
) {

    public GroupDevice convertToDomain() {
        return GroupDevice.builder()
                .id(this.id)
                .group(Group.builder().id(this.groupId).build())
                .device(Device.builder().id(this.deviceId).build())
                .build();
    }
}

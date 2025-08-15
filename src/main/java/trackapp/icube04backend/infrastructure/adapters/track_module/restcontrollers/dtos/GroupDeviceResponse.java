package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos;

import trackapp.icube04backend.modules.track_module.domain.models.GroupDevice;

public record GroupDeviceResponse(
        Long id,
        //GroupResponse group,
        Long groupId,
        //DeviceResponse device,
        Long deviceId,
        Long createdUserId,
        String createdAt
) {

    public static GroupDeviceResponse convertFromDomain(GroupDevice groupDevice) {
        return new GroupDeviceResponse(
                groupDevice.getId(),
                //GroupResponse.convertFromDomain(groupDevice.getGroup()),
                groupDevice.getGroup() != null ? groupDevice.getGroup().getId() : null,
                //DeviceResponse.convertFromDomain(groupDevice.getDevice()),
                groupDevice.getDevice() != null ? groupDevice.getDevice().getId() : null,
                groupDevice.getCreatedUser() != null ? groupDevice.getCreatedUser().getId() : null,
                groupDevice.getCreatedAt().toString()
        );
    }
}

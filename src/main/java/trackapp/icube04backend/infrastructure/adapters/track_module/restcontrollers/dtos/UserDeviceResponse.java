package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos;

import trackapp.icube04backend.modules.track_module.domain.models.UserDevice;

public record UserDeviceResponse(
        Long id,
        Long userId,
        DeviceResponse device,
        Long createdUserId,
        Long statusConfigDetailId,
        String createdAt
) {

    public static UserDeviceResponse convertFromDomain(UserDevice userDevice) {
        return new UserDeviceResponse(
                userDevice.getId(),
                userDevice.getUser() != null ? userDevice.getUser().getId() : null,
                DeviceResponse.convertFromDomain(userDevice.getDevice()),
                userDevice.getCreatedUser() != null ? userDevice.getCreatedUser().getId() : null,
                userDevice.getStatusConfigDetail() != null ? userDevice.getStatusConfigDetail().getId() : null,
                userDevice.getCreatedAt() != null
                        ? userDevice.getCreatedAt().toString()
                        : null
        );
    }
}

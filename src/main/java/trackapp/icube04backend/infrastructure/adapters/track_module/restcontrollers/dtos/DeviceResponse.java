package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos;

import trackapp.icube04backend.modules.track_module.domain.models.Device;

public record DeviceResponse(
        Long id,
        String name,
        String uniqueId,
        Long positionId,
        Long createdUserId,
        Long companyId,
        String createdAt,
        String lastUpdated
) {
    public static DeviceResponse convertFromDomain(Device device) {
        return new DeviceResponse(
                device.getId(),
                device.getName(),
                device.getUniqueId(),
                device.getPositionId() != null ? device.getPositionId() : null,
                device.getCreatedUser() != null
                        ? device.getCreatedUser().getId()
                        : null,
                device.getCompanyId(),
                device.getCreatedAt() != null
                        ? device.getCreatedAt().toString()
                        : null,
                device.getLastUpdated() != null
                        ? device.getLastUpdated().toString()
                        : null
        );
    }
}

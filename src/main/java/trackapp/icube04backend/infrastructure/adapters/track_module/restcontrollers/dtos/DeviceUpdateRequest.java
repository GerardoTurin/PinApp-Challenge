package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos;

import trackapp.icube04backend.modules.track_module.domain.models.Device;

public record DeviceUpdateRequest(
        Long id,
        String name,
        String uniqueId,
        Long positionId
) {

    public Device convertToDomain() {
        return Device.builder()
                .id(this.id)
                .name(this.name)
                .uniqueId(this.uniqueId)
                .positionId(this.positionId != null ? this.positionId : null)
                .build();
    }
}

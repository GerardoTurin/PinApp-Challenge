package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos;

import com.fasterxml.jackson.databind.JsonNode;
import trackapp.icube04backend.modules.track_module.domain.models.Device;
import trackapp.icube04backend.modules.track_module.domain.models.Position;

import java.time.LocalDateTime;
import java.util.List;

public record PositionCreateRequest(
        Double latitude,
        Double longitude,
        Double nearbyPoint,
        Double altitude,
        Double speed,
        String address,
        Long deviceId,
        List <JsonNode> attributes,
        String deviceTime,
        String fixTime
) {

    public Position convertToDomain() {
        return Position.builder()
                .latitude(this.latitude)
                .longitude(this.longitude)
                .nearbyPoint(this.nearbyPoint)
                .altitude(this.altitude)
                .speed(this.speed)
                .address(this.address)
                .device(Device.builder().id(this.deviceId).build())
                .attributes(this.attributes)
                .deviceTime(this.deviceTime != null ? LocalDateTime.parse(this.deviceTime) : null)
                .fixTime(this.fixTime != null ? LocalDateTime.parse(this.fixTime) : null)
                .build();
    }
}

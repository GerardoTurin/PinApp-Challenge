package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos;

import com.fasterxml.jackson.databind.JsonNode;
import trackapp.icube04backend.modules.track_module.domain.models.Position;

import java.util.List;

public record PositionResponse(
        Long id,
        Double latitude,
        Double longitude,
        Double nearbyPoint,
        Double altitude,
        Double speed,
        String address,
        Long deviceId,
        List <JsonNode> attributes,
        String deviceTime,
        String fixTime,
        String createdAt
) {

    public static PositionResponse convertFromDomain(Position position) {
        return new PositionResponse(
                position.getId(),
                position.getLatitude(),
                position.getLongitude(),
                position.getNearbyPoint(),
                position.getAltitude(),
                position.getSpeed(),
                position.getAddress(),
                position.getDevice() != null ? position.getDevice().getId() : null,
                position.getAttributes(),
                position.getDeviceTime() != null ? position.getDeviceTime().toString() : null,
                position.getFixTime() != null ? position.getFixTime().toString() : null,
                position.getCreatedAt().toString()
        );
    }
}

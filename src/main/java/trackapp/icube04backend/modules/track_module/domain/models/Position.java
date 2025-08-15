package trackapp.icube04backend.modules.track_module.domain.models;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Position {
    private Long id;
    private Double latitude;
    private Double longitude;
    private Double nearbyPoint;
    private Double altitude;
    private Double speed;
    private String address;
    private List <JsonNode> attributes;
    private Device device;
    private LocalDateTime serverTime;
    private LocalDateTime deviceTime;
    private LocalDateTime fixTime;
    private String nearestPoint;
    private LocalDateTime createdAt;

}

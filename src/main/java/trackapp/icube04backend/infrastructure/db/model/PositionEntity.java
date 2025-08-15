package trackapp.icube04backend.infrastructure.db.model;


import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import trackapp.icube04backend.modules.track_module.domain.models.Position;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "positions")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;
    private Double longitude;
    private Double nearbyPoint;
    private Double altitude;
    private Double speed;
    private String address;

    @JdbcTypeCode(SqlTypes.JSON)
    private List <JsonNode> attributes;


    //@Column(name = "device_id")
    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceEntity device;

    private LocalDateTime deviceTime;
    private LocalDateTime fixTime;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;



    public static PositionEntity convertFromDomain(Position position) {
        return PositionEntity.builder()
                .id(position.getId())
                .latitude(position.getLatitude())
                .longitude(position.getLongitude())
                .nearbyPoint(position.getNearbyPoint())
                .altitude(position.getAltitude())
                .speed(position.getSpeed())
                .address(position.getAddress())
                .attributes(position.getAttributes())
                .device(DeviceEntity.convertFromDomain(position.getDevice()))
                .deviceTime(position.getDeviceTime())
                .fixTime(position.getFixTime())
                .createdAt(position.getCreatedAt())
                .build();
    }



    public Position convertToDomain() {
        return Position.builder()
                .id(this.getId())
                .latitude(this.getLatitude())
                .longitude(this.getLongitude())
                .nearbyPoint(this.getNearbyPoint())
                .altitude(this.getAltitude())
                .speed(this.getSpeed())
                .address(this.getAddress())
                .attributes(this.getAttributes())
                .device(this.getDevice() != null ? this.getDevice().convertToDomain() : null)
                .deviceTime(this.getDeviceTime())
                .fixTime(this.getFixTime())
                .createdAt(this.getCreatedAt())
                .build();
    }
}

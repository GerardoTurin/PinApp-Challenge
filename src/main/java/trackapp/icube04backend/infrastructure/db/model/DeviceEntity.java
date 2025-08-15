package trackapp.icube04backend.infrastructure.db.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import trackapp.icube04backend.modules.track_module.domain.models.Device;

import java.time.LocalDateTime;

@Table(name = "devices")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String uniqueId;

    @Column(name = "position_id")
    private Long positionId;

    @ManyToOne
    @JoinColumn(name = "created_user_id")
    private UserEntity createdUser;

    private Long companyId;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_update")
    private LocalDateTime lastUpdated;


    public static DeviceEntity convertFromDomain(Device device) {
        return DeviceEntity.builder()
                .id(device.getId())
                .name(device.getName())
                .uniqueId(device.getUniqueId())
                .positionId(device.getPositionId() != null ? device.getPositionId() : null)
                .createdUser(UserEntity.convertFromDomain(device.getCreatedUser()))
                .companyId(device.getCompanyId())
                .createdAt(device.getCreatedAt())
                .lastUpdated(device.getLastUpdated())
                .build();
    }



    public Device convertToDomain() {
        return Device.builder()
                .id(this.getId())
                .name(this.getName())
                .uniqueId(this.getUniqueId())
                .positionId(this.getPositionId() != null ? this.getPositionId() : null)
                .createdUser(this.getCreatedUser() != null ? this.getCreatedUser().convertToDomain() : null)
                .companyId(this.getCompanyId())
                .createdAt(this.getCreatedAt())
                .lastUpdated(this.getLastUpdated())
                .build();
    }


}

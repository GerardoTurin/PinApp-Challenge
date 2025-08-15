package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import trackapp.icube04backend.modules.track_module.domain.models.UserDevice;

import java.time.LocalDateTime;

@Table(name = "user_devices")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceEntity device;

    @ManyToOne
    @JoinColumn(name = "created_user_id")
    private UserEntity createdUser;

    @ManyToOne
    @JoinColumn(name = "status_config_detail_id")
    private ConfigDetailEntity statusConfigDetail;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;



        public static UserDeviceEntity convertFromDomain(UserDevice userDevice) {
            return UserDeviceEntity.builder()
                    .id(userDevice.getId())
                    .user(UserEntity.convertFromDomain(userDevice.getUser()))
                    .device(DeviceEntity.convertFromDomain(userDevice.getDevice()))
                    .createdUser(UserEntity.convertFromDomain(userDevice.getCreatedUser()))
                    .statusConfigDetail(ConfigDetailEntity.convertFromDomain(userDevice.getStatusConfigDetail()))
                    .createdAt(userDevice.getCreatedAt())
                    .build();
        }



        public UserDevice convertToDomain() {
            return UserDevice.builder()
                    .id(this.getId())
                    .user(this.getUser() != null ? this.getUser().convertToDomain() : null)
                    .device(this.getDevice() != null ? this.getDevice().convertToDomain() : null)
                    .createdUser(this.getCreatedUser() != null ? this.getCreatedUser().convertToDomain() : null)
                    .statusConfigDetail(this.getStatusConfigDetail() != null ? this.getStatusConfigDetail().convertToDomain() : null)
                    .createdAt(this.getCreatedAt())
                    .build();
        }


    }

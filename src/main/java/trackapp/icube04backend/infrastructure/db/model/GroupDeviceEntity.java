package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import trackapp.icube04backend.modules.track_module.domain.models.GroupDevice;

import java.time.LocalDateTime;

@Table(name = "group_devices")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GroupDeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceEntity device;

    @ManyToOne
    @JoinColumn(name = "created_user_id")
    private UserEntity createdUser;


    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;




    public static GroupDeviceEntity convertFromDomain(GroupDevice groupDevice) {
        return GroupDeviceEntity.builder()
                .id(groupDevice.getId())
                .group(GroupEntity.convertFromDomain(groupDevice.getGroup()))
                .device(DeviceEntity.convertFromDomain(groupDevice.getDevice()))
                .createdUser(UserEntity.convertFromDomain(groupDevice.getCreatedUser()))
                .createdAt(groupDevice.getCreatedAt())
                .build();
    }



    public GroupDevice convertToDomain() {
        return GroupDevice.builder()
                .id(this.getId())
                .group(this.getGroup() != null ? this.getGroup().convertToDomain() : null)
                .device(this.getDevice() != null ? this.getDevice().convertToDomain() : null)
                .createdUser(this.getCreatedUser() != null ? this.getCreatedUser().convertToDomain() : null)
                .createdAt(this.getCreatedAt())
                .build();
    }

}

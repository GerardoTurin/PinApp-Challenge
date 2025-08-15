package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import trackapp.icube04backend.modules.track_module.domain.models.UserGroup;

import java.time.LocalDateTime;

@Table(name = "user_group")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @ManyToOne
    @JoinColumn(name = "created_user_id")
    private UserEntity createdUser;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


    public static UserGroupEntity convertFromDomain(UserGroup userGroup) {
        return UserGroupEntity.builder()
                .id(userGroup.getId())
                .user(UserEntity.convertFromDomain(userGroup.getUser()))
                .group(GroupEntity.convertFromDomain(userGroup.getGroup()))
                .createdUser(UserEntity.convertFromDomain(userGroup.getCreatedUser()))
                .createdAt(userGroup.getCreatedAt())
                .build();
    }



    public UserGroup convertToDomain() {
        return UserGroup.builder()
                .id(this.getId())
                .user(this.getUser() != null ? this.getUser().convertToDomain() : null)
                .group(this.getGroup() != null ? this.getGroup().convertToDomain() : null)
                .createdUser(this.getCreatedUser() != null ? this.getCreatedUser().convertToDomain() : null)
                .createdAt(this.getCreatedAt())
                .build();
    }
}

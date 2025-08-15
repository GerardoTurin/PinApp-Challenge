package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import trackapp.icube04backend.modules.track_module.domain.models.Group;

import java.time.LocalDateTime;

@Table(name = "groups")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;

    @ManyToOne
    @JoinColumn(name = "created_user_id")
    private UserEntity createdUser;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;


    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public static GroupEntity convertFromDomain(Group group) {
        return GroupEntity.builder()
                .id(group.getId())
                .name(group.getName())
                .code(group.getCode())
                .createdUser(UserEntity.convertFromDomain(group.getCreatedUser()))
                .company(CompanyEntity.convertFromDomain(group.getCompany()))
                .createdAt(group.getCreatedAt())
                .build();
    }


    public Group convertToDomain() {
        return Group.builder()
                .id(this.getId())
                .name(this.getName())
                .code(this.getCode())
                .createdUser(this.getCreatedUser() != null ? this.getCreatedUser().convertToDomain() : null)
                .company(this.getCompany() != null ? this.getCompany().convertToDomain() : null)
                .createdAt(this.getCreatedAt())
                .build();
    }
}

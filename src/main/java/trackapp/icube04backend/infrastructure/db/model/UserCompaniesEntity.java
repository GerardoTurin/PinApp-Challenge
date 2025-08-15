package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import trackapp.icube04backend.modules.track_module.domain.models.UsersCompanies;

import java.time.LocalDateTime;

@Table(name = "users_companies")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserCompaniesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @CreationTimestamp
    @Column(name = "create_at", updatable = false)
    private LocalDateTime createdAt;



    public static UserCompaniesEntity convertFromDomain(UsersCompanies usersCompanies) {
        return UserCompaniesEntity.builder()
                .id(usersCompanies.getId())
                .user(UserEntity.convertFromDomain(usersCompanies.getUser()))
                .company(CompanyEntity.convertFromDomain(usersCompanies.getCompany()))
                .createdAt(usersCompanies.getCreatedAt())
                .build();
    }



    public UsersCompanies convertToDomain() {
        return UsersCompanies.builder()
                .id(this.getId())
                .user(this.getUser() != null ? this.getUser().convertToDomain() : null)
                .company(this.getCompany() != null ? this.getCompany().convertToDomain() : null)
                .createdAt(this.getCreatedAt())
                .build();
    }
}

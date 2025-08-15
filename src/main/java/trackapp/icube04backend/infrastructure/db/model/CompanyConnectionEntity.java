package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import trackapp.icube04backend.modules.track_module.domain.models.CompanyConnection;

import java.time.LocalDateTime;

@Table(name = "company_connection")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CompanyConnectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    private String ip;
    private Integer port;
    private String userName;
    private String password;

    @ManyToOne
    @JoinColumn(name = "created_user_id", nullable = false)
    private UserEntity createdUser;

    private String appUrl;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


    public static CompanyConnectionEntity convertFromDomain(CompanyConnection companyConnection) {
        return CompanyConnectionEntity.builder()
                .id(companyConnection.getId())
                .company(CompanyEntity.convertFromDomain(companyConnection.getCompany()))
                .ip(companyConnection.getIp())
                .port(companyConnection.getPort())
                .userName(companyConnection.getUserName())
                .password(companyConnection.getPassword())
                .createdUser(UserEntity.convertFromDomain(companyConnection.getCreatedUser()))
                .appUrl(companyConnection.getAppUrl())
                .createdAt(companyConnection.getCreatedAt())
                .build();
    }




    public CompanyConnection convertToDomain() {
        return CompanyConnection.builder()
                .id(this.getId())
                .company(this.getCompany() != null ? this.getCompany().convertToDomain() : null)
                .ip(this.getIp())
                .port(this.getPort())
                .userName(this.getUserName())
                .password(this.getPassword())
                .createdUser(this.getCreatedUser() != null ? this.getCreatedUser().convertToDomain() : null)
                .appUrl(this.getAppUrl())
                .createdAt(this.getCreatedAt())
                .build();
    }


}

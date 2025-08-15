package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import trackapp.icube04backend.modules.track_module.domain.models.UserConnection;

@Table(name = "user_connection")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserConnectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private String userName;
    private String password;


    public static UserConnectionEntity convertFromDomain(UserConnection userConnection) {
        return UserConnectionEntity.builder()
                .id(userConnection.getId())
                .company(CompanyEntity.convertFromDomain(userConnection.getCompany()))
                .user(UserEntity.convertFromDomain(userConnection.getUser()))
                .userName(userConnection.getUserName())
                .password(userConnection.getPassword())
                .build();
    }




    public UserConnection convertToDomain() {
        return UserConnection.builder()
                .id(this.getId())
                .company(this.getCompany() != null ? this.getCompany().convertToDomain() : null)
                .user(this.getUser() != null ? this.getUser().convertToDomain() : null)
                .userName(this.getUserName())
                .password(this.getPassword())
                .build();
    }
}

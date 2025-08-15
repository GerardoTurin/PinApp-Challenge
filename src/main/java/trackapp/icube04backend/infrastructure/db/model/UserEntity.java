package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import trackapp.icube04backend.modules.auth_module.domain.models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "users")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String email;
    private boolean status; // Enabled - Disabled

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "user_type_config_detail_id")
    private ConfigDetailEntity userType;

    @ManyToMany
    @JoinTable(
            name = "users_companies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    private List<CompanyEntity> companies;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roles;

    private String imgUrl;
    private LocalDate updatedAt;



    public User convertToDomain(){
        return User.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .name(this.name)
                .lastname(this.lastname)
                .email(this.email)
                .status(this.status)
                //.userType(this.userType.convertToDomain())
                .userType(this.userType != null ? this.userType.convertToDomain() : null)
                .imgUrl(this.imgUrl)
                .updatedAt(this.updatedAt)
                .companies(this.companies != null ? this.companies.stream().map(CompanyEntity::convertToDomain).toList() : new ArrayList<>())
                .roles(this.roles != null ? this.roles.stream().map(RoleEntity::convertToDomain).toList() : new ArrayList<>())
                .build();
    }

    public static UserEntity convertFromDomain(User user){
        if (user == null) {
            return null;
        }

        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .status(user.isStatus())
                .userType(ConfigDetailEntity.convertFromDomain(user.getUserType()))
                .imgUrl(user.getImgUrl())
                .updatedAt(user.getUpdatedAt())
                .companies(user.getCompanies() != null ? user.getCompanies().stream().map(CompanyEntity::convertFromDomain).collect(Collectors.toList()) : new ArrayList<>())
                .roles(user.getRoles() != null ? user.getRoles().stream().map(RoleEntity::convertFromDomain).collect(Collectors.toList()) : new ArrayList<>())
                .build();
    }

}

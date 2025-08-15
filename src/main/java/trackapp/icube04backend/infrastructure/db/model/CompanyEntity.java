package trackapp.icube04backend.infrastructure.db.model;

import jakarta.persistence.*;
import lombok.*;
import trackapp.icube04backend.modules.auth_module.domain.models.User;
import trackapp.icube04backend.modules.company_module.domain.models.Company;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "companies")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String description;
    private String imgUrl;

    @ManyToMany
    @JoinTable(
            name = "users_companies",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> users;



    public Company convertToDomain() {
        return Company.builder()
                .id(this.getId())
                .name(getName())
                .address(getAddress())
                .description(getDescription())
                .usuarios(null) // Solo IDs y usernames
                .imgUrl(getImgUrl())
                .build();
    }



    public static CompanyEntity convertFromDomain(Company company){
        if (company == null) {
            return null;
        }

        return CompanyEntity.builder()
                .id(company.getId())
                .name(company.getName())
                .address(company.getAddress())
                .description(company.getDescription())
                .users(company.getUsuarios() != null ? company.getUsuarios().stream().map(UserEntity::convertFromDomain).collect(Collectors.toList()) :  new ArrayList<>())
                .imgUrl(company.getImgUrl())
                .build();
    }
}

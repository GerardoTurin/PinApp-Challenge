package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import trackapp.icube04backend.modules.auth_module.domain.models.Role;

@Table(name = "roles")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;



    public Role convertToDomain(){
        return Role.builder()
                .id(this.id)
                .name(this.name)
                .company(this.company.convertToDomain())
                .build();
    }

    public static RoleEntity convertFromDomain(Role role){
        return RoleEntity.builder()
                .id(role.getId())
                .name(role.getName())
                .company(CompanyEntity.convertFromDomain(role.getCompany()))
                .build();
    }
}

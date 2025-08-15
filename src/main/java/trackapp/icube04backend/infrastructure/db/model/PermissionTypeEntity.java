package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import trackapp.icube04backend.modules.auth_module.domain.models.PermissionType;

@Table(name = "permission_types")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    public PermissionType convertToDomain(){
        return PermissionType.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }

    public static PermissionTypeEntity convertFromDomain(PermissionType permissionType){
        return PermissionTypeEntity.builder()
                .id(permissionType.getId())
                .name(permissionType.getName())
                .build();
    }
}

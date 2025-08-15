package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import trackapp.icube04backend.modules.auth_module.domain.models.ModulesRolesPermissions;

@Table(name = "modules_roles_permissions")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModulesRolesPermissionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private PermissionTypeEntity permission;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private ModuleAppEntity module;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItemEntity menuItem;


    public ModulesRolesPermissions convertToDomain(){
        return ModulesRolesPermissions.builder()
                .id(this.id)
                .role(this.role.convertToDomain())
                .permissionType(this.permission.convertToDomain())
                .moduleApp(this.module.convertToDomain())
                .menuItem(this.menuItem != null ? menuItem.convertToDomain() : null)
                .build();
    }

    public static ModulesRolesPermissionsEntity convertFromDomain(ModulesRolesPermissions modulesRolesPermissions){
        return ModulesRolesPermissionsEntity.builder()
                .id(modulesRolesPermissions.getId())
                .role(RoleEntity.convertFromDomain(modulesRolesPermissions.getRole()))
                .permission(PermissionTypeEntity.convertFromDomain(modulesRolesPermissions.getPermissionType()))
                .module(ModuleAppEntity.convertFromDomain(modulesRolesPermissions.getModuleApp()))
                .menuItem(modulesRolesPermissions.getMenuItem() != null ? MenuItemEntity.convertFromDomain(modulesRolesPermissions.getMenuItem()) : null)
                .build();
    }
}

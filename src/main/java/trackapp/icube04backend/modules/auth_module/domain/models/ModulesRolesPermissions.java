package trackapp.icube04backend.modules.auth_module.domain.models;


import lombok.*;
import trackapp.icube04backend.modules.catalog_module.domain.models.MenuItem;
import trackapp.icube04backend.modules.catalog_module.domain.models.ModuleApp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModulesRolesPermissions {
    private Long id;
    private Role role;
    private PermissionType permissionType;
    private ModuleApp moduleApp;
    private MenuItem menuItem;
}

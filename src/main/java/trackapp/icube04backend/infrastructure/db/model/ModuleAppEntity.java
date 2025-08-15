package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import trackapp.icube04backend.modules.catalog_module.domain.models.ModuleApp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "modules_app")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ModuleAppEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private String icon;
    private String path;

    @OneToMany(mappedBy = "moduleApp", fetch = FetchType.LAZY) // OneToMany = Cada ModuleApp puede tener varios MenuItems asociados (hijos en este caso) --- mappedBy = moduleApp es el campo de la otra parte de la relacion en la otra entidad
    private List<MenuItemEntity> menuItems = new ArrayList<>();



    public ModuleApp convertToDomain() {
        return ModuleApp.builder()
                .id(getId())
                .label(getLabel())
                .icon(getIcon())
                .path(getPath())
                .childrenMenuItemList(getMenuItems() != null ? getMenuItems().stream().map(MenuItemEntity -> MenuItemEntity.convertToDomain()).collect(Collectors.toList()) : new ArrayList<>())
                .build();
    }



    public static ModuleAppEntity convertFromDomain(ModuleApp moduleApp){
        return ModuleAppEntity.builder()
                .id(moduleApp.getId())
                .label(moduleApp.getLabel())
                .icon(moduleApp.getIcon())
                .path(moduleApp.getPath())
                .menuItems(moduleApp.getChildrenMenuItemList() != null ? moduleApp.getChildrenMenuItemList().stream().map(MenuItemEntity::convertFromDomain).collect(Collectors.toList()) : new ArrayList<>())
                .build();
    }
}

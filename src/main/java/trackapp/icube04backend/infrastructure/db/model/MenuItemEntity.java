package trackapp.icube04backend.infrastructure.db.model;

import jakarta.persistence.*;
import lombok.*;
import trackapp.icube04backend.modules.catalog_module.domain.models.MenuItem;
import trackapp.icube04backend.modules.catalog_module.domain.models.ModuleApp;

@Table(name = "menu_items")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MenuItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private String icon;
    private String path;
    private Long position;

    @ManyToOne  // ManyToOne = varios menu_items estan relacionados (tienen como padre) a un module_app
    @JoinColumn(name = "module_app_id", nullable = false) // name = nombre del campo en la tabla MenuItemEntity que hace referencia al ID de la tabla padre
    private ModuleAppEntity moduleApp;


    public MenuItem convertToDomain(){
        return MenuItem.builder()
                .id(getId())
                .label(getLabel())
                .icon(getIcon())
                .path(getPath())
                .position(getPosition())
                .moduleApp(ModuleApp.builder()
                        .id(getModuleApp().getId())
                        .label(getModuleApp().getLabel())
                        .icon(getModuleApp().getIcon())
                        .path(getModuleApp().getPath())
                        .build())
                .build();
    }

    public static MenuItemEntity convertFromDomain(MenuItem menuItem){
        return MenuItemEntity.builder()
                .id(menuItem.getId())
                .label(menuItem.getLabel())
                .icon(menuItem.getIcon())
                .path(menuItem.getPath())
                .position(menuItem.getPosition())
                .moduleApp(ModuleAppEntity.convertFromDomain(menuItem.getModuleApp()))
                .build();
    }
}

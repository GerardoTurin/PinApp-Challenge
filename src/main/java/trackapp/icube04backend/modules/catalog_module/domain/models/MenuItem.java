package trackapp.icube04backend.modules.catalog_module.domain.models;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItem {
    private Long id;
    private String label;
    private String icon;
    private String path;
    private Long position;
    private ModuleApp moduleApp; // es su parent
}

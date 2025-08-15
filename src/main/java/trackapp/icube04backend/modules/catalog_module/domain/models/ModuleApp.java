package trackapp.icube04backend.modules.catalog_module.domain.models;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleApp {
    private Long id;
    private String label;
    private String icon;
    private String path;
    private List<MenuItem> childrenMenuItemList;
}

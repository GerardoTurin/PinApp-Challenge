package trackapp.icube04backend.modules.configuration_module.domain.models;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfigType {
    private Long id;
    private String name;
    // private ConfigTypesCODEenum code;
    private String code;
    private boolean catalog;
}

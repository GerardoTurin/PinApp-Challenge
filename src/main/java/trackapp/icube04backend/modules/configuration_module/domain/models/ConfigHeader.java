package trackapp.icube04backend.modules.configuration_module.domain.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfigHeader {
    private Long id;
    private String name;
    private String code;
    private boolean systemParameter;
    private ConfigType configType;
}

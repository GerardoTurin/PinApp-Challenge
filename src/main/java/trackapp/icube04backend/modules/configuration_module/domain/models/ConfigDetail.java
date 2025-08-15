package trackapp.icube04backend.modules.configuration_module.domain.models;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import trackapp.icube04backend.modules.company_module.domain.models.Company;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfigDetail {
    private Long id;
    private String name;
    // private ConfigDetailsCODEenum code;
    private String code;
    private Company company;
    private ConfigHeader configHeader;
    private Long position;
    private ConfigDetail parent;
    private boolean isSystemParameter;
    private JsonNode attribute;
}

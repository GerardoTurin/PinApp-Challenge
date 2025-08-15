package trackapp.icube04backend.modules.configuration_module.domain.models;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfigDetailHierarchy {
    private Long id;
    private ConfigDetail configDetail;
    private ConfigDetail configDetailParent;
    private ConfigDetail configDetailSon;
    private JsonNode attribute;
}

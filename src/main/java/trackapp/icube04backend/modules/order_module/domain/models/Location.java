package trackapp.icube04backend.modules.order_module.domain.models;


import lombok.*;
import trackapp.icube04backend.modules.company_module.domain.models.Company;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {
    private Long id;
    private String name;
    private String address;
    private String gpsCoordinates;
    private ConfigDetail coordinatesType;
    private ConfigDetail category;
    private ConfigDetail status;
    private Company company;
    private List<ConfigDetail> locationTypes;

}

package trackapp.icube04backend.modules.order_module.domain.models;


import lombok.*;
import trackapp.icube04backend.modules.company_module.domain.models.Company;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
    private Long id;
    private String name;
    private String code;
    private LocalDate updatedAt;
    private ConfigDetail status;
    private Company company;
}

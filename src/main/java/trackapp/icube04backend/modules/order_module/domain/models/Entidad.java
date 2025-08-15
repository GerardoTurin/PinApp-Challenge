package trackapp.icube04backend.modules.order_module.domain.models;


import lombok.*;
import trackapp.icube04backend.modules.company_module.domain.models.Company;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Entidad {
    private Long id;
    private String identification;
    private String name;
    private String lastname;
    private String address;
    private String phone;
    private String email;
    private ConfigDetail identificationType;
    private ConfigDetail entidadType;
    private ConfigDetail status;
    private Company company;
}

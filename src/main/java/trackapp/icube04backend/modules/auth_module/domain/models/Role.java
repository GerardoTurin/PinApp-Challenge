package trackapp.icube04backend.modules.auth_module.domain.models;


import lombok.*;
import trackapp.icube04backend.modules.company_module.domain.models.Company;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    private Long id;
    private String name;
    private Company company;
}

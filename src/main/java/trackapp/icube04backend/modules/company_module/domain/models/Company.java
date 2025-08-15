package trackapp.icube04backend.modules.company_module.domain.models;

import lombok.*;
import trackapp.icube04backend.modules.auth_module.domain.models.User;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
    private Long id;
    private String name;
    private String address;
    private String description;
    private String imgUrl;
    private List<User> usuarios; // Empleados que trabajan en la empresa
}

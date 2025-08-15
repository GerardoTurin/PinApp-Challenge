package trackapp.icube04backend.modules.auth_module.domain.models;

import lombok.*;
import trackapp.icube04backend.modules.company_module.domain.models.Company;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String email;
    private boolean status; // Enabled - Disabled
    private ConfigDetail userType;
    private String imgUrl;
    private LocalDate updatedAt;
    private List<Company> companies; // Compañias en las que trabaja el usuario
    private List<Role> roles;
}

package trackapp.icube04backend.modules.track_module.domain.models;

import lombok.*;
import trackapp.icube04backend.modules.auth_module.domain.models.User;
import trackapp.icube04backend.modules.company_module.domain.models.Company;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserConnection {

    private Long id;
    private Company company;
    private User user;
    private String userName;
    private String password;
}

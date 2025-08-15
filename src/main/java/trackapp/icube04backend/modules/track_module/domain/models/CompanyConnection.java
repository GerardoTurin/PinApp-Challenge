package trackapp.icube04backend.modules.track_module.domain.models;

import lombok.*;
import trackapp.icube04backend.modules.auth_module.domain.models.User;
import trackapp.icube04backend.modules.company_module.domain.models.Company;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyConnection {
    private Long id;
    private Company company;
    private String ip;
    private Integer port;
    private String userName;
    private String password;
    private User createdUser;
    private String appUrl;
    private LocalDateTime createdAt;
}

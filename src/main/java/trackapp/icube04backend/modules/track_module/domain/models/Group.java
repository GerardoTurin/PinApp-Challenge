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
public class Group {
    private Long id;
    private String name;
    private String code;
    private User createdUser;
    private Company company;
    private LocalDateTime createdAt;
}

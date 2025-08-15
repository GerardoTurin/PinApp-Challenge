package trackapp.icube04backend.modules.track_module.domain.models;

import lombok.*;
import trackapp.icube04backend.modules.auth_module.domain.models.User;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGroup {
    private Long id;
    private User user;
    private Group group;
    private User createdUser;
    private LocalDateTime createdAt;
}

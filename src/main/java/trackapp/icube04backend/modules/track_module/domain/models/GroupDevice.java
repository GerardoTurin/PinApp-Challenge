package trackapp.icube04backend.modules.track_module.domain.models;


import lombok.*;
import trackapp.icube04backend.modules.auth_module.domain.models.User;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupDevice {
    private Long id;
    private Group group;
    private Device device;
    private User createdUser;
    private LocalDateTime createdAt;
}

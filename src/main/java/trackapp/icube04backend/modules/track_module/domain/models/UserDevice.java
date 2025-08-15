package trackapp.icube04backend.modules.track_module.domain.models;

import lombok.*;
import trackapp.icube04backend.modules.auth_module.domain.models.User;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDevice {
    private Long id;
    private User user;
    private Device device;
    private User createdUser;
    private ConfigDetail statusConfigDetail;
    private LocalDateTime createdAt;

}

package trackapp.icube04backend.modules.track_module.domain.models;

import lombok.*;
import trackapp.icube04backend.modules.auth_module.domain.models.User;


import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Device {
    private Long id;
    private String name;
    private String uniqueId;
    private Long positionId;
    private Position position;
    private User createdUser;
    private Long companyId;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;
}

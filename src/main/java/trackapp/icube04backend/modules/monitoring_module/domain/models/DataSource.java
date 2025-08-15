package trackapp.icube04backend.modules.monitoring_module.domain.models;


import lombok.*;
import trackapp.icube04backend.modules.auth_module.domain.models.User;
import trackapp.icube04backend.modules.company_module.domain.models.Company;
import trackapp.icube04backend.modules.order_module.domain.models.Entidad;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataSource {
    private Long id;
    private String urlConnection;
    private String userConnection;
    private String passwordConnection;
    private String driverConnection;
    private String endpointConnection;
    private Entidad entityId;
    private Company companyId;
    private LocalDateTime createdAt;
    private User createdUser;
}

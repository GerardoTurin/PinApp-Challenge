package trackapp.icube04backend.modules.auth_module.domain.models;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionType {
    private Long id;
    private String name;
}

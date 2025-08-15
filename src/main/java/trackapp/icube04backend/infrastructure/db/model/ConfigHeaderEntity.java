package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigHeader;

@Table(name = "config_headers")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ConfigHeaderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private boolean systemParameter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "config_type_id")
    private ConfigTypeEntity configType;


    public ConfigHeader convertToDomain() {
        return ConfigHeader.builder()
                .id(getId())
                .name(getName())
                .configType(getConfigType().convertToDomain())
                .code(getCode())
                .systemParameter(isSystemParameter())
                .build();
    }



    public static ConfigHeaderEntity convertFromDomain(ConfigHeader configHeader){
        return ConfigHeaderEntity.builder()
                .id(configHeader.getId())
                .name(configHeader.getName())
                .configType(ConfigTypeEntity.convertFromDomain(configHeader.getConfigType()))
                .systemParameter(configHeader.isSystemParameter())
                .build();
    }
}

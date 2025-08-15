package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigType;


@Table(name = "config_types")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ConfigTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean catalog;
    private String code;


    public ConfigType convertToDomain() {
        return ConfigType.builder()
                .id(getId())
                .name(getName())
                .catalog(isCatalog())
                .code(getCode())
                .build();
    }



    public static ConfigTypeEntity convertFromDomain(ConfigType configType){
        return ConfigTypeEntity.builder()
                .id(configType.getId())
                .name(configType.getName())
                .catalog(configType.isCatalog())
                .build();
    }
}

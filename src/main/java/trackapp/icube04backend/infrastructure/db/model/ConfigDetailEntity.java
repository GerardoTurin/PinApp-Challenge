package trackapp.icube04backend.infrastructure.db.model;


import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;

@Table(name = "config_details")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ConfigDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private Long position;
    private boolean system_parameter;

    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode attribute;

    @ManyToOne
    @JoinColumn(name = "config_header_id")
    private ConfigHeaderEntity configHeader;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity companyEntity;

    @ManyToOne
    @JoinColumn(name = "parent")
    private ConfigDetailEntity parent;





    public ConfigDetail convertToDomain() {
        return ConfigDetail.builder()
                .id(getId())
                .name(getName())
                .code(getCode())
                .company(getCompanyEntity() != null ? getCompanyEntity().convertToDomain() : null)
                .configHeader(getConfigHeader() != null ? getConfigHeader().convertToDomain() : null)
                .parent(getParent() != null ? getParent().convertToDomain() : null)
                .position(getPosition())
                .isSystemParameter(isSystem_parameter())
                .attribute(getAttribute())
                .build();
    }



    public static ConfigDetailEntity convertFromDomain(ConfigDetail configType) {
        if (configType == null) {
            return null;
        }

        return ConfigDetailEntity.builder()
                .id(configType.getId())
                .name(configType.getName())
                // .code()  CONFIGURAR
                .configHeader(configType.getConfigHeader() != null ? ConfigHeaderEntity.convertFromDomain(configType.getConfigHeader()) : null)
                .companyEntity(configType.getCompany() != null ? CompanyEntity.convertFromDomain(configType.getCompany()) : null)
                .position(configType.getPosition() != null ? configType.getPosition() : null)
                .parent(configType.getParent() != null ? ConfigDetailEntity.convertFromDomain(configType.getParent()) : null)
                .system_parameter(configType.isSystemParameter())
                .attribute(configType.getAttribute() != null ? configType.getAttribute() : null)
                .build();
    }

}

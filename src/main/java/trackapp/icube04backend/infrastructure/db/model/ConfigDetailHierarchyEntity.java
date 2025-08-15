package trackapp.icube04backend.infrastructure.db.model;


import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetailHierarchy;

@Table(name = "config_detail_hierarchy")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ConfigDetailHierarchyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "config_detail_id")
    private ConfigDetailEntity ConfigDetail;

    @ManyToOne
    @JoinColumn(name = "parent_config_detail_id")
    private ConfigDetailEntity ConfigDetailParent;

    @ManyToOne
    @JoinColumn(name = "son_config_detail_id")
    private ConfigDetailEntity ConfigDetailSon;

    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode attribute;


    public ConfigDetailHierarchy convertToDomain(){
        return ConfigDetailHierarchy.builder()
                .id(getId())
                .configDetail(getConfigDetail().convertToDomain())
                .configDetailParent(getConfigDetailParent() != null ? getConfigDetailParent().convertToDomain() : null)
                .configDetailSon(getConfigDetailSon().convertToDomain())
                .attribute(getAttribute())
                .build();
    }

    public static ConfigDetailHierarchyEntity convertFromDomain(ConfigDetailHierarchy configDetailHierarchy){
        return ConfigDetailHierarchyEntity.builder()
                .id(configDetailHierarchy.getId())
                .ConfigDetail(ConfigDetailEntity.convertFromDomain(configDetailHierarchy.getConfigDetail()))
                .ConfigDetailParent(configDetailHierarchy.getConfigDetailParent() != null ? ConfigDetailEntity.convertFromDomain(configDetailHierarchy.getConfigDetailParent()) : null)
                .ConfigDetailSon(ConfigDetailEntity.convertFromDomain(configDetailHierarchy.getConfigDetailSon()))
                .attribute(configDetailHierarchy.getAttribute())
                .build();
    }
}

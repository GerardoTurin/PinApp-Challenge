package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import trackapp.icube04backend.modules.monitoring_module.domain.models.DataSource;

import java.time.LocalDateTime;

@Entity
@Table(name = "data_source")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DataSourceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url_connection;
    private String user_connection;
    private String password_connection;
    private String driver_connection;
    private String endpoint_connection;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntidadEntity entity;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    private LocalDateTime created_at;

    @ManyToOne
    @JoinColumn(name = "created_user")
    private UserEntity userCreator;



    public DataSource convertToDomain() {
        return DataSource.builder()
                .id(getId())
                .urlConnection(getUrl_connection())
                .userConnection(getUser_connection())
                .passwordConnection(getPassword_connection())
                .driverConnection(getDriver_connection())
                .endpointConnection(getEndpoint_connection())
                .entityId(getEntity() == null ? null : getEntity().convertToDomain())
                .companyId(getCompany() == null ? null : getCompany().convertToDomain())
                .createdAt(getCreated_at())
                .createdUser(getUserCreator() == null ? null : getUserCreator().convertToDomain())
                .build();
    }

    public static DataSourceEntity convertFromDomain(DataSource dataSource) {
        return DataSourceEntity.builder()
                .id(dataSource.getId())
                .url_connection(dataSource.getUrlConnection())
                .user_connection(dataSource.getUserConnection())
                .password_connection(dataSource.getPasswordConnection())
                .driver_connection(dataSource.getDriverConnection())
                .endpoint_connection(dataSource.getEndpointConnection())
                .entity(dataSource.getEntityId() == null ? null : EntidadEntity.convertFromDomain(dataSource.getEntityId()))
                .company(dataSource.getCompanyId() == null ? null : CompanyEntity.convertFromDomain(dataSource.getCompanyId()))
                .created_at(dataSource.getCreatedAt())
                .userCreator(dataSource.getCreatedUser() == null ? null : UserEntity.convertFromDomain(dataSource.getCreatedUser()))
                .build();
    }
}

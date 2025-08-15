package trackapp.icube04backend.infrastructure.db.model;

import jakarta.persistence.*;
import lombok.*;
import trackapp.icube04backend.modules.order_module.domain.models.Entidad;

@Table(name = "entities")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EntidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identification;
    private String name;
    private String lastname;
    private String address;
    private String phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "identification_type_config_detail_id")
    private ConfigDetailEntity identificationType;

    @ManyToOne
    @JoinColumn(name = "entidad_type_config_detail_id")
    private ConfigDetailEntity entidadType;

    @ManyToOne
    @JoinColumn(name = "status_config_detail_id")
    private ConfigDetailEntity status;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;


    public Entidad convertToDomain() {
        return Entidad.builder()
                .id(getId())
                .identification(getIdentification())
                .name(getName())
                .lastname(getLastname())
                .address(getAddress())
                .phone(getPhone())
                .email(getEmail())
                .identificationType(getIdentificationType() != null ? getIdentificationType().convertToDomain() : null)
                .entidadType(getEntidadType() != null ? getEntidadType().convertToDomain() : null)
                .status(getStatus() != null ? getStatus().convertToDomain() : null)
                .company(getCompany().convertToDomain())
                .build();
    }

    public static EntidadEntity convertFromDomain(Entidad entidad){
        return EntidadEntity.builder()
                .id(entidad.getId())
                .identification(entidad.getIdentification())
                .name(entidad.getName())
                .lastname(entidad.getLastname())
                .address(entidad.getAddress())
                .phone(entidad.getPhone())
                .email(entidad.getEmail())
                .identificationType(ConfigDetailEntity.convertFromDomain(entidad.getIdentificationType()))
                .entidadType(ConfigDetailEntity.convertFromDomain(entidad.getEntidadType()))
                .status(ConfigDetailEntity.convertFromDomain(entidad.getStatus()))
                .company(CompanyEntity.convertFromDomain(entidad.getCompany()))
                .build();
    }
}

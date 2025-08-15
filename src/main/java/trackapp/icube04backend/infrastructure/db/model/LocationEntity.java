package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;
import trackapp.icube04backend.modules.order_module.domain.models.Location;

import java.util.ArrayList;
import java.util.List;

@Table(name = "locations")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String gps_coordinates;


    @ManyToOne
    @JoinColumn(name = "gps_coordinates_type_config_detail_id")
    private ConfigDetailEntity coordinatesType;

    @ManyToOne
    @JoinColumn(name = "category_config_detail_id")
    private ConfigDetailEntity category;

    @ManyToOne
    @JoinColumn(name = "status_config_detail_id")
    private ConfigDetailEntity status;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;


    @ManyToMany
    @JoinTable(
            name = "locations_locationstypes",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "location_type_config_detail_id")
    )
    private List<ConfigDetailEntity> locationTypes = new ArrayList<>();



    public Location convertToDomain() {
        return Location.builder()
                .id(getId())
                .name(getName())
                .address(getAddress())
                .gpsCoordinates(getGps_coordinates())
                .coordinatesType(getCoordinatesType() != null ? getCoordinatesType().convertToDomain() : null)
                .category(getCategory() != null ? getCategory().convertToDomain() : null)
                .status(getStatus() != null ? getStatus().convertToDomain() : null)
                .company(getCompany().convertToDomain())
                .locationTypes(getLocationTypes() != null ?
                        getLocationTypes().stream()
                                .map(configdetail -> ConfigDetail.builder()
                                        .id(configdetail.getId())
                                        .name(configdetail.getName())
                                        .build()
                                )
                                .toList()
                        : null)
                .build();
    }


    public static LocationEntity convertFromDomain(Location location){
        return LocationEntity.builder()
                .id(location.getId())
                .name(location.getName())
                .address(location.getAddress())
                .gps_coordinates(location.getGpsCoordinates())
                .coordinatesType(ConfigDetailEntity.convertFromDomain(location.getCoordinatesType()))
                .category(ConfigDetailEntity.convertFromDomain(location.getCategory()))
                .status(ConfigDetailEntity.convertFromDomain(location.getStatus()))
                .company(CompanyEntity.convertFromDomain(location.getCompany()))
                .locationTypes(location.getLocationTypes().stream()
                        .map(ConfigDetailEntity::convertFromDomain).toList())
                .build();
    }
}

package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import trackapp.icube04backend.modules.order_module.domain.models.OrdersLocations;

@Table(name = "orders_locations")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrdersLocationsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;

    @ManyToOne
    @JoinColumn(name = "status_config_detail_id")
    private ConfigDetailEntity status;

    @ManyToOne
    @JoinColumn(name = "location_type_config_detail_id")
    private ConfigDetailEntity locationType;

    private Long position;


    public OrdersLocations convertToDomain(){
        return OrdersLocations.builder()
                .id(getId())
                .order(getOrder().convertToDomain())
                .location(getLocation().convertToDomain())
                .status(getStatus().convertToDomain())
                .position(getPosition())
                .locationType(locationType.convertToDomain())
                .build();
    }

    public static OrdersLocationsEntity convertFromDomain(OrdersLocations ordersLocations){
        return OrdersLocationsEntity.builder()
                .id(ordersLocations.getId())
                .order(OrderEntity.convertFromDomain(ordersLocations.getOrder()))
                .location(LocationEntity.convertFromDomain(ordersLocations.getLocation()))
                .status(ConfigDetailEntity.convertFromDomain(ordersLocations.getStatus()))
                .position(ordersLocations.getPosition())
                .locationType(ConfigDetailEntity.convertFromDomain(ordersLocations.getLocationType()))
                .build();
    }


}

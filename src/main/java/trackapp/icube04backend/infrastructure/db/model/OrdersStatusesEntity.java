package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import trackapp.icube04backend.modules.order_module.domain.models.OrdersStatuses;

import java.time.LocalDate;

@Table(name = "orders_statuses")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrdersStatusesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "status_config_detail_id")
    private ConfigDetailEntity status;

    private LocalDate created_at;


    public OrdersStatuses convertToDomain(){
        return OrdersStatuses.builder()
                .id(getId())
                .order(getOrder().convertToDomain())
                .status(getStatus().convertToDomain())
                .build();
    }

    public static OrdersStatusesEntity convertFromDomain(OrdersStatuses ordersStatuses){
        return OrdersStatusesEntity.builder()
                .id(ordersStatuses.getId())
                .order(ordersStatuses.getOrder() != null ? OrderEntity.convertFromDomain(ordersStatuses.getOrder()) : null)
                .status(ordersStatuses.getStatus() != null ? ConfigDetailEntity.convertFromDomain(ordersStatuses.getStatus()) : null)
                .build();
    }


}

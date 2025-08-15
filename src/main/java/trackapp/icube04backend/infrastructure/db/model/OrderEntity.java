package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import trackapp.icube04backend.modules.order_module.domain.models.Order;

import java.time.LocalDate;

@Table(name = "orders")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate order_date;
    private LocalDate updated_at;

    @Column(columnDefinition = "boolean default true")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntidadEntity entity;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private BookingEntity booking;

    @ManyToOne
    @JoinColumn(name = "operation_type_config_detail_id")
    private ConfigDetailEntity operationType;

    @ManyToOne
    @JoinColumn(name = "status_config_detail_id")
    private ConfigDetailEntity status;





    public Order convertToDomain(){
        return Order.builder()
                .id(getId())
                .orderDate(getOrder_date())
                .entity(getEntity() != null ? getEntity().convertToDomain() : null)
                .booking(getBooking() != null ? getBooking().convertToDomain() : null)
                .active(isActive())
                .operationType(getOperationType() != null ? getOperationType().convertToDomain() : null)
                .status(getStatus() != null ? getStatus().convertToDomain() : null)
                .updatedAt(getUpdated_at())
                .build();
    }


    public static OrderEntity convertFromDomain(Order order){
        return OrderEntity.builder()
                .id(order.getId())
                .order_date(order.getOrderDate())
                .entity(EntidadEntity.convertFromDomain(order.getEntity()))
                .booking(BookingEntity.convertFromDomain(order.getBooking()))
                .active(order.isActive())
                .operationType(ConfigDetailEntity.convertFromDomain(order.getOperationType()))
                .status(ConfigDetailEntity.convertFromDomain(order.getStatus()))
                .updated_at(order.getUpdatedAt())
                .build();
    }

}

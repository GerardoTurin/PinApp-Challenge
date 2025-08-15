package trackapp.icube04backend.modules.order_module.domain.models;


import lombok.*;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private Long id;
    private LocalDate orderDate;
    private Entidad entity;
    private Booking booking;
    private boolean active;
    private ConfigDetail operationType;
    private ConfigDetail status;
    private LocalDate updatedAt;
}

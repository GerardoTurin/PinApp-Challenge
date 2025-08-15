package trackapp.icube04backend.modules.order_module.domain.models;


import lombok.*;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersLocations {
    private Long id;
    private Order order;
    private Location location;
    private ConfigDetail status;
    private Long position;
    private ConfigDetail locationType;
}

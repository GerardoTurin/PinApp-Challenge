package trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.responses;

import lombok.Builder;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;

import trackapp.icube04backend.modules.order_module.domain.models.Booking;
import trackapp.icube04backend.modules.order_module.domain.models.Entidad;
import trackapp.icube04backend.modules.order_module.domain.models.OrdersLocations;
import trackapp.icube04backend.modules.order_module.domain.models.OrdersStatuses;

import java.time.LocalDate;
import java.util.List;

@Builder
public record CustomOrderOrderLocationsOrderStatusesResponse(
        Long id,
        LocalDate orderDate,
        Entidad entity,
        Booking booking,
        boolean active,
        ConfigDetail operationType,
        ConfigDetail status,
        List<OrdersLocations> ordersLocations,
        List<OrdersStatuses> ordersStatuses
) {
}

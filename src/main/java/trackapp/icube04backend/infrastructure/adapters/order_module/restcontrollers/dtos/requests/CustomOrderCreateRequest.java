package trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests;

import java.time.LocalDate;
import java.util.List;

public record CustomOrderCreateRequest(
        LocalDate date,
        Long entityId,
        Long bookingId,
        Long operationTypeId,
        Long statusId,
        List<CustomOrdersLocationsRequest> orderslocations
) {
}

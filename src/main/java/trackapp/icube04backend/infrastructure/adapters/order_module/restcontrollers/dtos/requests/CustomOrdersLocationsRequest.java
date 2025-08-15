package trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests;

public record CustomOrdersLocationsRequest(
        Long locationId,
        Long position,
        Long statusConfigDetailId,
        Long locationTypeId
) {
}

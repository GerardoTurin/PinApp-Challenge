package trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests;

public record CustomBookingUpdateRequest(
        Long id,
        String name,
        String code,
        Long statusBookingId
) {
}

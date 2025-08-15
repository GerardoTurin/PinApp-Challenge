package trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests;

public record CustomBookingCreateRequest(
        String name,
        String code,
        Long statusBookingId
) {
}

package trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests;

public record CustomEntidadCreateRequest(
        String identification,
        String name,
        String lastname,
        String address,
        String phone,
        String email,
        Long identificationTypeId,
        Long entidadTypeId,
        Long statusId
) {
}

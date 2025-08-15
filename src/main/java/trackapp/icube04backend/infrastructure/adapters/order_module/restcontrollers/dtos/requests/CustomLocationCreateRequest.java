package trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests;

import java.util.List;

public record CustomLocationCreateRequest(
        String name,
        String address,
        String gpsCoordinates,
        Long gpsCoordinatesTypeId,
        Long categoryId,
        Long statusId,
        List<Long> locationTypes
) {
}

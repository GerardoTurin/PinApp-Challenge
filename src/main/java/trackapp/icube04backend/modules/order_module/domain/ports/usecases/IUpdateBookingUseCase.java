package trackapp.icube04backend.modules.order_module.domain.ports.usecases;

import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomBookingUpdateRequest;
import trackapp.icube04backend.modules.order_module.domain.models.Booking;

public interface IUpdateBookingUseCase {
    Booking update(CustomBookingUpdateRequest request);
}

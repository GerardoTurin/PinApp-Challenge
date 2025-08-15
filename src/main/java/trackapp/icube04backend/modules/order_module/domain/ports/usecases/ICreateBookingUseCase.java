package trackapp.icube04backend.modules.order_module.domain.ports.usecases;

import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomBookingCreateRequest;
import trackapp.icube04backend.modules.order_module.domain.models.Booking;

public interface ICreateBookingUseCase {
    Booking create(CustomBookingCreateRequest request);
}

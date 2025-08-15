package trackapp.icube04backend.modules.order_module.domain.ports.usecases;

import trackapp.icube04backend.modules.order_module.domain.models.Booking;

import java.util.List;

public interface IGetBookingsUseCase {
    List<Booking> getAllByCompanyId();
}

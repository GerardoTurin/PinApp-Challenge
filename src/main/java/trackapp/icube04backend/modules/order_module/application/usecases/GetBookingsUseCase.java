package trackapp.icube04backend.modules.order_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.order_module.domain.models.Booking;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.IBookingRepository;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IGetBookingsUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBookingsUseCase implements IGetBookingsUseCase {

    private final IBookingRepository bookingRepository;
    private final ISessionService sessionService;

    @Override
    public List<Booking> getAllByCompanyId() {
        var companyid = sessionService.getCompanyId();

        return bookingRepository.getAllByCompanyId(companyid);
    }
}

package trackapp.icube04backend.modules.order_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomBookingCreateRequest;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.company_module.domain.ports.repositories.ICompanyRepository;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailRepository;
import trackapp.icube04backend.modules.order_module.domain.models.Booking;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.IBookingRepository;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.ICreateBookingUseCase;

@Service
@RequiredArgsConstructor
public class CreateBookingUseCase implements ICreateBookingUseCase {

    private final IBookingRepository bookingRepository;
    private final IConfigDetailRepository configDetailRepository;
    private final ISessionService sessionService;
    private final ICompanyRepository companyRepository;

    @Override
    public Booking create(CustomBookingCreateRequest request) {
        var cf = configDetailRepository.findById(request.statusBookingId());
        var company = companyRepository.findById(sessionService.getCompanyId());

        return bookingRepository.save(Booking.builder()
                        .name(request.name())
                        .code(request.code())
                        .status(cf)
                        .company(company)
                .build());
    }
}

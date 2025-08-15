package trackapp.icube04backend.modules.order_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomBookingUpdateRequest;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailRepository;
import trackapp.icube04backend.modules.order_module.domain.models.Booking;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.IBookingRepository;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IUpdateBookingUseCase;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UpdateBookingUseCase implements IUpdateBookingUseCase {

    private final IBookingRepository bookingRepository;
    private final IConfigDetailRepository configDetailRepository;

    @Override
    public Booking update(CustomBookingUpdateRequest request) {
        var old = bookingRepository.findById(request.id());
        var cd = configDetailRepository.findById(request.statusBookingId());

        old.setName(request.name());
        old.setCode(request.code());
        old.setStatus(cd);
        old.setUpdatedAt(LocalDate.now());

        return bookingRepository.save(old);
    }
}

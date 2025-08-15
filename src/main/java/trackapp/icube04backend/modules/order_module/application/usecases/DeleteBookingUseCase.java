package trackapp.icube04backend.modules.order_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.IBookingRepository;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IDeleteBookingUseCase;

@Service
@RequiredArgsConstructor
public class DeleteBookingUseCase implements IDeleteBookingUseCase {

    private final IBookingRepository bookingRepository;

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }
}

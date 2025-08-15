package trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomBookingCreateRequest;
import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomBookingUpdateRequest;
import trackapp.icube04backend.modules.order_module.domain.models.Booking;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.ICreateBookingUseCase;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IDeleteBookingUseCase;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IGetBookingsUseCase;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IUpdateBookingUseCase;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@Slf4j
public class BookingRestController {

    private final ICreateBookingUseCase createBookingUseCase;
    private final IUpdateBookingUseCase updateBookingUseCase;
    private final IDeleteBookingUseCase deleteBookingUseCase;
    private final IGetBookingsUseCase getBookingsUseCase;

    @PostMapping("/create")
    public Booking create(@RequestBody CustomBookingCreateRequest request){
        return createBookingUseCase.create(request);
    }
    @PutMapping("/update")
    public Booking update(@RequestBody CustomBookingUpdateRequest request){
        return updateBookingUseCase.update(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        deleteBookingUseCase.delete(id);
    }

    @GetMapping("/all")
    public List<Booking> getAllByCompanyId(){
        return getBookingsUseCase.getAllByCompanyId();
    }

}

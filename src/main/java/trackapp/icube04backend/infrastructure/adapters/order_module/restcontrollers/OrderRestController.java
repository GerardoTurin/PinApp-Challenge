package trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomOrderCreateRequest;
import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomOrderUpdateRequest;
import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.responses.CustomOrderOrderLocationsOrderStatusesResponse;
import trackapp.icube04backend.modules.order_module.domain.models.Order;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.ICreateOrderUseCase;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IDeleteOrderUseCase;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IGetOrdersUseCase;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IUpdateOrderUseCase;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderRestController {

    private final ICreateOrderUseCase createOrderUseCase;
    private final IUpdateOrderUseCase updateOrderUseCase;
    private final IDeleteOrderUseCase deleteOrderUseCase;
    private final IGetOrdersUseCase getOrdersUseCase;

    @PostMapping("/create")
    public Order create(@RequestBody CustomOrderCreateRequest request){
        return createOrderUseCase.create(request);
    }

    @GetMapping("/all/{date}")
    public List<CustomOrderOrderLocationsOrderStatusesResponse> getAllByDateCompanyAndActive(@PathVariable LocalDate date){
        return getOrdersUseCase.getAllByDateCompanyAndActive(date);
    }


    @PutMapping("/update")
    public Order update(@RequestBody CustomOrderUpdateRequest request){
        return updateOrderUseCase.update(request);
    }

    // El borrado de una orden no es un borado convencional, solo pasa de un activo true a false
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        deleteOrderUseCase.delete(id);
    }
}

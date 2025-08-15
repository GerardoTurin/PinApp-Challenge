package trackapp.icube04backend.modules.order_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.modules.order_module.domain.models.OrdersStatuses;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.IOrdersStatusesRepository;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.ICreateOrderStatusesUseCase;

@Service
@RequiredArgsConstructor
public class CreateOrderStatusesUseCase implements ICreateOrderStatusesUseCase {

    private final IOrdersStatusesRepository ordersStatusesRepository;

    @Override
    public void create(OrdersStatuses orderstatus) {
        ordersStatusesRepository.save(orderstatus);
    }
}

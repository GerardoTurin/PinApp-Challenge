package trackapp.icube04backend.modules.order_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.IOrderRepository;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IDeleteOrderUseCase;

@Service
@RequiredArgsConstructor
public class DeleteOrderUseCase implements IDeleteOrderUseCase {

    private final IOrderRepository orderRepository;

    @Override
    public void delete(Long id) {
        var order = orderRepository.findById(id);

        order.setActive(false);

        orderRepository.save(order);
    }
}

package trackapp.icube04backend.modules.order_module.domain.ports.repositories;

import trackapp.icube04backend.modules.order_module.domain.models.OrdersStatuses;

import java.util.List;

public interface IOrdersStatusesRepository {
    void save(OrdersStatuses orderstatus);

    List<OrdersStatuses> findByOrderId(Long id);
}

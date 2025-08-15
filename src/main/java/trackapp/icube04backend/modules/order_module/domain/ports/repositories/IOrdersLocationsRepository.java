package trackapp.icube04backend.modules.order_module.domain.ports.repositories;

import trackapp.icube04backend.modules.order_module.domain.models.OrdersLocations;

import java.util.List;

public interface IOrdersLocationsRepository {
    void save(OrdersLocations ordersLocations);

    List<OrdersLocations> findByOrderId(Long id);

    void delete(Long id);
}

package trackapp.icube04backend.modules.order_module.domain.ports.repositories;

import trackapp.icube04backend.modules.order_module.domain.models.Order;

import java.time.LocalDate;
import java.util.List;

public interface IOrderRepository {
    Order save(Order order);

    Order findById(Long id);

    void delete(Long id);

    List<Order> findAllByDateAndCompanyIdAndActive(LocalDate date, Long companyId);
}

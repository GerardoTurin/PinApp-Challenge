package trackapp.icube04backend.infrastructure.adapters.order_module.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import trackapp.icube04backend.infrastructure.db.jpa.OrderJPARepository;
import trackapp.icube04backend.infrastructure.db.model.OrderEntity;
import trackapp.icube04backend.modules.order_module.domain.models.Order;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.IOrderRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository implements IOrderRepository {

    private final OrderJPARepository jpaRepository;

    @Override
    public Order save(Order order) {
        return jpaRepository.save(OrderEntity.convertFromDomain(order)).convertToDomain();
    }

    @Override
    public Order findById(Long id) {
        return jpaRepository.findById(id).get().convertToDomain();
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<Order> findAllByDateAndCompanyIdAndActive(LocalDate date, Long companyId) {
        return jpaRepository.findAllByOrderDateAndCompanyIdAndActive(date, companyId, true).stream().map(OrderEntity::convertToDomain).toList();
    }
}

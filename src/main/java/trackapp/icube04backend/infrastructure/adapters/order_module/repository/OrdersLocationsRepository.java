package trackapp.icube04backend.infrastructure.adapters.order_module.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.db.jpa.OrdersLocationsJPARepository;
import trackapp.icube04backend.infrastructure.db.model.OrdersLocationsEntity;
import trackapp.icube04backend.modules.order_module.domain.models.OrdersLocations;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.IOrdersLocationsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersLocationsRepository implements IOrdersLocationsRepository {

    private final OrdersLocationsJPARepository jpaRepository;

    @Override
    public void save(OrdersLocations ordersLocations) {
        jpaRepository.save(OrdersLocationsEntity.convertFromDomain(ordersLocations));
    }

    @Override
    public List<OrdersLocations> findByOrderId(Long id) {
        return jpaRepository.findAllByOrderId(id).stream().map(OrdersLocationsEntity::convertToDomain).toList();
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }
}

package trackapp.icube04backend.infrastructure.adapters.order_module.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import trackapp.icube04backend.infrastructure.db.jpa.OrdersStatusesJPARepository;
import trackapp.icube04backend.infrastructure.db.model.OrdersStatusesEntity;
import trackapp.icube04backend.modules.order_module.domain.models.OrdersStatuses;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.IOrdersStatusesRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrdersStatusesRepository implements IOrdersStatusesRepository {

    private final OrdersStatusesJPARepository jpaRepository;

    @Override
    public void save(OrdersStatuses orderstatus) {
        jpaRepository.save(OrdersStatusesEntity.convertFromDomain(orderstatus));
    }

    @Override
    public List<OrdersStatuses> findByOrderId(Long id) {
        return jpaRepository.findByOrderId(id).stream().map(OrdersStatusesEntity::convertToDomain).toList();
    }
}

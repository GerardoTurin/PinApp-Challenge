package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import trackapp.icube04backend.infrastructure.db.model.OrdersStatusesEntity;

import java.util.List;

public interface OrdersStatusesJPARepository extends JpaRepository<OrdersStatusesEntity, Long> {

    @Query("SELECT ol FROM OrdersStatusesEntity ol WHERE ol.order.id = :id")
    List<OrdersStatusesEntity> findByOrderId(@Param("id") Long id);
}

package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import trackapp.icube04backend.infrastructure.db.model.OrdersLocationsEntity;

import java.util.List;

public interface OrdersLocationsJPARepository extends JpaRepository<OrdersLocationsEntity, Long> {

    @Query("SELECT ol FROM OrdersLocationsEntity ol WHERE ol.order.id = :id")
    List<OrdersLocationsEntity> findAllByOrderId(@Param("id") Long id);

}

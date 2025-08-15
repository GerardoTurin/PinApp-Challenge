package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import trackapp.icube04backend.infrastructure.db.model.OrderEntity;

import java.time.LocalDate;
import java.util.List;

public interface OrderJPARepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT o FROM OrderEntity o " +
            "WHERE o.entity.company.id = :companyId " +
            "AND o.active = :active " +
            "AND o.order_date = :orderDate")
    List<OrderEntity> findAllByOrderDateAndCompanyIdAndActive(@Param("orderDate") LocalDate orderDate, @Param("companyId") Long companyId, @Param("active") Boolean active);

}

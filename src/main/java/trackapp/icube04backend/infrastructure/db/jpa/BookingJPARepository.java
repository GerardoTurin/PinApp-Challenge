package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import trackapp.icube04backend.infrastructure.db.model.BookingEntity;

import java.util.List;

public interface BookingJPARepository extends JpaRepository<BookingEntity, Long> {

    @Query("SELECT c FROM BookingEntity c WHERE c.company.id = :companyid")
    List<BookingEntity> getAllByCompanyId(Long companyid);


}

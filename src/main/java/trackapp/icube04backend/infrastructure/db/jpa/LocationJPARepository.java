package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import trackapp.icube04backend.infrastructure.db.model.LocationEntity;

import java.util.List;

public interface LocationJPARepository extends JpaRepository<LocationEntity, Long> {

    @Query("SELECT c FROM LocationEntity c WHERE c.company.id = :companyId")
    List<LocationEntity> findAllByCompanyId(Long companyId);
}
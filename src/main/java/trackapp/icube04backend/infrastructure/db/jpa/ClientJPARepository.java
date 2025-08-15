package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import trackapp.icube04backend.infrastructure.db.jpa.projections.ClientAgeAggregatesProjection;
import trackapp.icube04backend.infrastructure.db.model.ClientEntity;

import java.util.List;

public interface ClientJPARepository extends JpaRepository<ClientEntity, Long> {


    @Query("SELECT c FROM ClientEntity c " +
            "WHERE c.companyId = :companyId " +
            "AND c.userId = :userId")
    List<ClientEntity> findAllByCompanyIdAndByUserId(Long companyId, Long userId);


    @Query(value = """
        SELECT 
            COUNT(*) AS total,
            ROUND(AVG(age)::numeric, 2)::float8 AS averageAge,
            ROUND(COALESCE(STDDEV_POP(age), 0)::numeric, 2)::float8 AS stdDevAge
        FROM clients
        WHERE age IS NOT NULL
        """, nativeQuery = true)
    ClientAgeAggregatesProjection computeAgeAggregatesAll();
}

package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import trackapp.icube04backend.infrastructure.db.model.DataSourceEntity;

import java.util.List;
import java.util.Optional;

public interface DataSourceJPARepository extends JpaRepository<DataSourceEntity, Long> {

    @Query(value = "SELECT d FROM DataSourceEntity d WHERE d.id=:dsId AND d.company.id=:company_id")
    Optional<DataSourceEntity> findByIdxComp(@Param("dsId") Long dsId, @Param("company_id") Long company_id);

    List<DataSourceEntity> findByEntity_IdAndCompany_Id (Long entityId, Long companyId);

    Optional<DataSourceEntity> findByIdAndCompany_Id(Long id, Long companyId);

    List<DataSourceEntity> findByCompany_Id (Long companyId);
}

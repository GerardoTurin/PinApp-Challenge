package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import trackapp.icube04backend.infrastructure.db.model.CompanyConnectionEntity;

import java.util.List;

public interface CompanyConnectionJPARepository extends JpaRepository<CompanyConnectionEntity, Long>  {

//    @Query("SELECT cc FROM CompanyConnectionEntity cc WHERE cc.company.id = :companyId")
//    List<CompanyConnectionEntity> findAllByCompanyId(@Param("companyId") Long companyId);

    @Query("SELECT cc FROM CompanyConnectionEntity cc")
    List<CompanyConnectionEntity> findAllByCompanyId();


    @Query("SELECT cc FROM CompanyConnectionEntity cc WHERE cc.createdUser.id = :userId")
    List<CompanyConnectionEntity> findAllByUserId(@Param("userId")  Long userId);


}

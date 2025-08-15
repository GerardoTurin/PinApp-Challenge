package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import trackapp.icube04backend.infrastructure.db.model.UserCompaniesEntity;

import java.util.List;

public interface UserCompaniesJPARepository extends JpaRepository <UserCompaniesEntity, Long> {

    // List<UserCompaniesEntity> findAllByCompanyId(Long companyId);

    @Query("SELECT u FROM UserCompaniesEntity u WHERE u.user.id = :userId")
    List<UserCompaniesEntity> findAllByUserId(@Param("userId") Long userId);
}

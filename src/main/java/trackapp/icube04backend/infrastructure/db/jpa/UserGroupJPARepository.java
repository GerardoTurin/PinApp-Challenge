package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import trackapp.icube04backend.infrastructure.db.model.UserGroupEntity;

import java.util.List;

public interface UserGroupJPARepository extends JpaRepository <UserGroupEntity, Long> {


    @Query("SELECT ug FROM UserGroupEntity ug WHERE ug.group.company.id = :companyId AND ug.createdUser.id = :userId")
    List<UserGroupEntity> findAllByCompanyIdAndByUserId(Long companyId, Long userId);
}

package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import trackapp.icube04backend.infrastructure.db.model.GroupEntity;

import java.util.List;

public interface GroupJPARepository extends JpaRepository <GroupEntity, Long> {


    @Query("SELECT g FROM GroupEntity g WHERE g.company.id = :companyId")
    List<GroupEntity> findAllByCompanyId(Long companyId);

    @Query("SELECT g FROM GroupEntity g WHERE g.company.id = :companyId AND g.createdUser.id = :userId")
    List<GroupEntity> findAllByCompanyIdAndByUserId(Long companyId, Long userId);
}

package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import trackapp.icube04backend.infrastructure.db.model.GroupDeviceEntity;

import java.util.List;

public interface GroupDeviceJPARepository extends JpaRepository <GroupDeviceEntity, Long> {


    @Query("SELECT gd FROM GroupDeviceEntity gd WHERE gd.group.company.id = :companyId AND gd.createdUser.id = :userId")
    List<GroupDeviceEntity> findAllByCompanyIdAndByUserId(Long companyId, Long userId);
}

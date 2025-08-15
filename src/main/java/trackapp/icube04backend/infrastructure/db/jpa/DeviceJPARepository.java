package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import trackapp.icube04backend.infrastructure.db.model.DeviceEntity;

import java.util.List;

public interface DeviceJPARepository extends JpaRepository<DeviceEntity, Long> {

    @Query("SELECT d FROM DeviceEntity d " +
            "WHERE d.companyId = :companyId " +
            "AND d.createdUser.id = :userId")
    List<DeviceEntity> findAllByCompanyIdAndByUserId(Long companyId, Long userId);

    @Query("SELECT d FROM DeviceEntity d " +
            "WHERE d.companyId = :companyId")
    List<DeviceEntity> findAllByCompanyId(Long companyId);


    @Query("SELECT d FROM DeviceEntity d WHERE d.createdUser.id = :userId")
    List<DeviceEntity> findAllByUserId(Long userId);
}

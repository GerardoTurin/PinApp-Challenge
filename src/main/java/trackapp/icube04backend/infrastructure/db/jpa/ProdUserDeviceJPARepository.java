package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import trackapp.icube04backend.infrastructure.db.model.UserDeviceEntity;

import java.util.List;

public interface ProdUserDeviceJPARepository extends JpaRepository<UserDeviceEntity, Long> {

    @Query("SELECT ud FROM UserDeviceEntity ud "
            + "WHERE ud.device.companyId = :companyId "
            + "  AND ud.user.id = :userId")
    List<UserDeviceEntity> findAllByCompanyIdAndByUserId(
            Long companyId, Long userId);
}

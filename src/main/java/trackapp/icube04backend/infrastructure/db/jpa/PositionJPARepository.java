package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import trackapp.icube04backend.infrastructure.db.model.PositionEntity;

import java.util.List;

public interface PositionJPARepository extends JpaRepository<PositionEntity, Long> {



    @Query("SELECT p FROM PositionEntity p " +
            "WHERE p.device.id = :deviceId " +
            "ORDER BY p.createdAt DESC")
    List<PositionEntity> findAllByDeviceId(Long deviceId);
}

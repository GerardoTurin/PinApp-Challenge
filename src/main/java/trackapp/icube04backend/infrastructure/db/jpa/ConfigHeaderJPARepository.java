package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import trackapp.icube04backend.infrastructure.db.model.ConfigHeaderEntity;

import java.util.List;

public interface ConfigHeaderJPARepository extends JpaRepository<ConfigHeaderEntity, Long> {

    @Query("SELECT c FROM ConfigHeaderEntity c WHERE c.configType.id = :id")
    List<ConfigHeaderEntity> findByConfigTypeId(@Param("id") Long id);

}

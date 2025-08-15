package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import trackapp.icube04backend.infrastructure.db.model.ConfigDetailEntity;

import java.util.List;

public interface ConfigDetailJPARepository extends JpaRepository<ConfigDetailEntity, Long> {

    @Query("SELECT c FROM ConfigDetailEntity c WHERE c.code = :code")
    ConfigDetailEntity findByCode(@Param("code") String code);

    @Query("SELECT c FROM ConfigDetailEntity c WHERE c.configHeader.code = :code")
    List<ConfigDetailEntity> findByHeaderCode(@Param("code") String code);


}

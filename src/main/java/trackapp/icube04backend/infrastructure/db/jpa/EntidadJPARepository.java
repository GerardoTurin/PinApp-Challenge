package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import trackapp.icube04backend.infrastructure.db.model.EntidadEntity;

import java.util.List;

public interface EntidadJPARepository extends JpaRepository<EntidadEntity, Long> {

    @Query("SELECT e FROM EntidadEntity e WHERE e.company.id = :companyId AND e.entidadType.id = :typeId")
    List<EntidadEntity> findByCompanyIdAndEntidadType(@Param("typeId") Long typeId, @Param("companyId") Long companyId);

}

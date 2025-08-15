package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import trackapp.icube04backend.infrastructure.db.model.ConfigDetailHierarchyEntity;

import java.util.List;

public interface ConfigDetailHierarchyJPARepository extends JpaRepository<ConfigDetailHierarchyEntity, Long> {

    @Query("SELECT c FROM ConfigDetailHierarchyEntity c WHERE c.ConfigDetail.id = :configDetailId AND c.ConfigDetailParent.id = :configDetailParentId")
    List<ConfigDetailHierarchyEntity> findByConfigDetailIdAndConfigDetailParentId(@Param("configDetailId") Long configDetailId, @Param("configDetailParentId") Long configDetailParentId);

    @Query("SELECT c FROM ConfigDetailHierarchyEntity c WHERE c.ConfigDetail.id = :configDetailId")
    List<ConfigDetailHierarchyEntity> findByConfigDetailId(@Param("configDetailId") Long configDetailId);
}

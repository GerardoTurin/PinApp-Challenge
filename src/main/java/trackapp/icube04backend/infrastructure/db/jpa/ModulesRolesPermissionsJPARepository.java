package trackapp.icube04backend.infrastructure.db.jpa;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import trackapp.icube04backend.infrastructure.db.model.ModulesRolesPermissionsEntity;

import java.util.List;

public interface ModulesRolesPermissionsJPARepository extends JpaRepository<ModulesRolesPermissionsEntity, Long> {

    @Query("SELECT p FROM ModulesRolesPermissionsEntity p  WHERE p.role.id = :roleId")
    List<ModulesRolesPermissionsEntity> findByRoleId(@Param("roleId") Long roleId);

    @Modifying
    @Transactional
    @Query("DELETE FROM ModulesRolesPermissionsEntity p WHERE p.role.id = :roleId")
    void deleteByRoleId(@Param("roleId") Long roleId);

}

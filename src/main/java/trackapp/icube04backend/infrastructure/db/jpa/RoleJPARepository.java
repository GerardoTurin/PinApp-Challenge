package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import trackapp.icube04backend.infrastructure.db.model.RoleEntity;

public interface RoleJPARepository extends JpaRepository<RoleEntity, Long> {
}

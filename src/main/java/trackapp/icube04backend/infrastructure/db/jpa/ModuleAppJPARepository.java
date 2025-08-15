package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import trackapp.icube04backend.infrastructure.db.model.ModuleAppEntity;

public interface ModuleAppJPARepository extends JpaRepository<ModuleAppEntity, Long> {
}

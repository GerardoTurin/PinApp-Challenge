package trackapp.icube04backend.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import trackapp.icube04backend.infrastructure.db.model.CompanyEntity;

public interface CompanyJPARepository extends JpaRepository<CompanyEntity, Long> {
}

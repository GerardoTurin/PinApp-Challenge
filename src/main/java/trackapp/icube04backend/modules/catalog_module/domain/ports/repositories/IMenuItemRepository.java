package trackapp.icube04backend.modules.catalog_module.domain.ports.repositories;

import org.springframework.stereotype.Repository;
import trackapp.icube04backend.modules.catalog_module.domain.models.MenuItem;

@Repository
public interface IMenuItemRepository {

    MenuItem findById(Long id);
}

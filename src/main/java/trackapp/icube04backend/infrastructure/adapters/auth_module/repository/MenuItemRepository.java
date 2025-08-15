package trackapp.icube04backend.infrastructure.adapters.auth_module.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.db.jpa.MenuItemJPARepository;
import trackapp.icube04backend.modules.catalog_module.domain.models.MenuItem;
import trackapp.icube04backend.modules.catalog_module.domain.ports.repositories.IMenuItemRepository;

@Service
@RequiredArgsConstructor
public class MenuItemRepository implements IMenuItemRepository {

    private final MenuItemJPARepository jpaRepository;

    @Override
    public MenuItem findById(Long id) {
        return jpaRepository.findById(id).get().convertToDomain();
    }
}

package trackapp.icube04backend.modules.catalog_module.domain.ports.repositories;

import org.springframework.stereotype.Repository;
import trackapp.icube04backend.modules.catalog_module.domain.models.ModuleApp;

import java.util.List;

@Repository
public interface IModuleAppRepository {

    List<ModuleApp> findAll();

    ModuleApp findById(Long id);
}

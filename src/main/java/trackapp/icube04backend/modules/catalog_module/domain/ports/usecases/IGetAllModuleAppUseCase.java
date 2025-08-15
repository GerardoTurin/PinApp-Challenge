package trackapp.icube04backend.modules.catalog_module.domain.ports.usecases;

import trackapp.icube04backend.modules.catalog_module.domain.models.ModuleApp;

import java.util.List;

public interface IGetAllModuleAppUseCase {

    List<ModuleApp> getAll();
}

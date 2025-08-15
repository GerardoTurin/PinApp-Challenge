package trackapp.icube04backend.modules.catalog_module.application.usecases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.modules.catalog_module.domain.models.ModuleApp;
import trackapp.icube04backend.modules.catalog_module.domain.ports.repositories.IModuleAppRepository;
import trackapp.icube04backend.modules.catalog_module.domain.ports.usecases.IGetAllModuleAppUseCase;

import java.util.List;


@UseCase
@RequiredArgsConstructor
public class GetAllModuleAppUseCase implements IGetAllModuleAppUseCase {

    private final IModuleAppRepository moduleAppRepository;

    @Override
    public List<ModuleApp> getAll() {
        return moduleAppRepository.findAll();
    }
}

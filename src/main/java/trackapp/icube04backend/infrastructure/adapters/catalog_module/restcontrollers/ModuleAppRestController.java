package trackapp.icube04backend.infrastructure.adapters.catalog_module.restcontrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trackapp.icube04backend.modules.catalog_module.domain.models.ModuleApp;
import trackapp.icube04backend.modules.catalog_module.domain.ports.usecases.IGetAllModuleAppUseCase;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/moduleapp")
public class ModuleAppRestController {

    private final IGetAllModuleAppUseCase getAllModuleAppUseCase;

    @GetMapping("/all")
    public List<ModuleApp> getAll() {
        var modules = getAllModuleAppUseCase.getAll();

        return modules;
    }
}

package trackapp.icube04backend.infrastructure.adapters.configuration_module.restcontrollers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigType;
import trackapp.icube04backend.modules.configuration_module.domain.ports.usecases.IGetConfigTypesUseCase;

import java.util.List;

@RestController
    @RequestMapping("/configtypes")
@RequiredArgsConstructor
@Slf4j
public class ConfigTypeRestController {

    private final IGetConfigTypesUseCase getConfigTypesUseCase;

    @GetMapping("/all")
    public List<ConfigType> getAllCatalogTrue(){
        return getConfigTypesUseCase.getAll();
    }
}

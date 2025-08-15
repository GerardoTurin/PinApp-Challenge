package trackapp.icube04backend.infrastructure.adapters.configuration_module.restcontrollers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigHeader;
import trackapp.icube04backend.modules.configuration_module.domain.ports.usecases.IGetConfigHeadersUseCase;

import java.util.List;

@RestController
@RequestMapping("/configheaders")
@RequiredArgsConstructor
@Slf4j
public class ConfigHeaderRestController {

    private final IGetConfigHeadersUseCase getConfigHeadersUseCase;

    @GetMapping("/configTypeId/{id}")
    public List<ConfigHeader> getAllByConfigTypeId(@PathVariable Long id){
        return getConfigHeadersUseCase.getAllByConfigTypeId(id);
    }
}

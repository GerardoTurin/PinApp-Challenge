package trackapp.icube04backend.infrastructure.adapters.configuration_module.restcontrollers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import trackapp.icube04backend.infrastructure.adapters.configuration_module.restcontrollers.dtos.requests.CustomConfigDetailHierarchyCreateRequest;
import trackapp.icube04backend.infrastructure.adapters.configuration_module.restcontrollers.dtos.requests.CustomConfigDetailHierarchyUpdateRequest;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetailHierarchy;
import trackapp.icube04backend.modules.configuration_module.domain.ports.usecases.ICreateConfigDetailHierarchyUseCase;
import trackapp.icube04backend.modules.configuration_module.domain.ports.usecases.IDeleteConfigDetailHierarchyUseCase;
import trackapp.icube04backend.modules.configuration_module.domain.ports.usecases.IGetConfigDetailHierarchiesUseCase;
import trackapp.icube04backend.modules.configuration_module.domain.ports.usecases.IUpdateConfigDetailHierarchyUseCase;

import java.util.List;

@RestController
@RequestMapping("/configdetailshierarchy")
@RequiredArgsConstructor
@Slf4j
public class ConfigDetailHierarchyRestController {

    private final IGetConfigDetailHierarchiesUseCase getConfigDetailHierarchiesUseCase;
    private final ICreateConfigDetailHierarchyUseCase createConfigDetailHierarchyUseCase;
    private final IUpdateConfigDetailHierarchyUseCase updateConfigDetailHierarchyUseCase;
    private final IDeleteConfigDetailHierarchyUseCase deleteConfigDetailHierarchyUseCase;

    @GetMapping("/{configDetailId}/{configDetailParentId}")
    public List<ConfigDetailHierarchy> getByCode(@PathVariable Long configDetailId, @PathVariable Long configDetailParentId) {
        return getConfigDetailHierarchiesUseCase.getByConfigDetailIdAndConfigDetailParentId(configDetailId, configDetailParentId);
    }

    @PostMapping("/create")
    public ConfigDetailHierarchy create(@RequestBody CustomConfigDetailHierarchyCreateRequest request) {
        return createConfigDetailHierarchyUseCase.create(request);
    }


    @PutMapping("/update")
    public ConfigDetailHierarchy update(@RequestBody CustomConfigDetailHierarchyUpdateRequest request){
        return updateConfigDetailHierarchyUseCase.update(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        deleteConfigDetailHierarchyUseCase.delete(id);
    }
}

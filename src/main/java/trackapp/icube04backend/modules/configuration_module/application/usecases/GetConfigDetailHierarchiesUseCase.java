package trackapp.icube04backend.modules.configuration_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetailHierarchy;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailHierarchyRepository;
import trackapp.icube04backend.modules.configuration_module.domain.ports.usecases.IGetConfigDetailHierarchiesUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetConfigDetailHierarchiesUseCase implements IGetConfigDetailHierarchiesUseCase {

    private final IConfigDetailHierarchyRepository configDetailHierarchyRepository;

    @Override
    public List<ConfigDetailHierarchy> getByConfigDetailIdAndConfigDetailParentId(Long configDetailId, Long configDetailParentId) {

        if(configDetailParentId.equals(-1L)){
            return configDetailHierarchyRepository.findByConfigDetailId(configDetailId);
        }else{
            return configDetailHierarchyRepository.findByConfigDetailIdAndConfigDetailParentId(configDetailId, configDetailParentId);
        }


    }
}

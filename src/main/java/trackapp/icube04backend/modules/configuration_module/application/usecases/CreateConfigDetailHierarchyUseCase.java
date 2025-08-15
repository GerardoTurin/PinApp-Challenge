package trackapp.icube04backend.modules.configuration_module.application.usecases;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.adapters.configuration_module.repository.ConfigDetailHierarchyRepository;
import trackapp.icube04backend.infrastructure.adapters.configuration_module.restcontrollers.dtos.requests.CustomConfigDetailHierarchyCreateRequest;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetailHierarchy;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailRepository;
import trackapp.icube04backend.modules.configuration_module.domain.ports.usecases.ICreateConfigDetailHierarchyUseCase;

@Service
@RequiredArgsConstructor
public class CreateConfigDetailHierarchyUseCase implements ICreateConfigDetailHierarchyUseCase {


    private final IConfigDetailRepository configDetailRepository;
    private final ConfigDetailHierarchyRepository configDetailHierarchyRepository;

    @Override
    public ConfigDetailHierarchy create(CustomConfigDetailHierarchyCreateRequest request) {
        var cd = configDetailRepository.findById(request.configDetailId());
        var cdp = request.parentConfigDetailId() != null ? configDetailRepository.findById(request.parentConfigDetailId()) : null;
        var cds = configDetailRepository.findById(request.sonConfigDetailId());


        return configDetailHierarchyRepository.save(
                ConfigDetailHierarchy.builder()
                        .configDetail(cd)
                        .configDetailParent(cdp)
                        .configDetailSon(cds)
                        .attribute(request.attribute())
                        .build()
        );
    }
}

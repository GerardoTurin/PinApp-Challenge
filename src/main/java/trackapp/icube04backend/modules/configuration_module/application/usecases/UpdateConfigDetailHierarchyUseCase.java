package trackapp.icube04backend.modules.configuration_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.adapters.configuration_module.restcontrollers.dtos.requests.CustomConfigDetailHierarchyUpdateRequest;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetailHierarchy;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailHierarchyRepository;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailRepository;
import trackapp.icube04backend.modules.configuration_module.domain.ports.usecases.IUpdateConfigDetailHierarchyUseCase;

@Service
@RequiredArgsConstructor
public class UpdateConfigDetailHierarchyUseCase implements IUpdateConfigDetailHierarchyUseCase {

    private final IConfigDetailRepository configDetailRepository;
    private final IConfigDetailHierarchyRepository configDetailHierarchyRepository;

    @Override
    public ConfigDetailHierarchy update(CustomConfigDetailHierarchyUpdateRequest request) {
        var old = configDetailHierarchyRepository.findById(request.id());

        var cd = configDetailRepository.findById(request.configDetailId());
        var cdp = request.parentConfigDetailId() != null ? configDetailRepository.findById(request.parentConfigDetailId()) : null;
        var cds = configDetailRepository.findById(request.sonConfigDetailId());

        old.setConfigDetail(cd);
        old.setConfigDetailParent(cdp);
        old.setConfigDetailSon(cds);
        old.setAttribute(request.attribute());

        return configDetailHierarchyRepository.save(old);
    }
}

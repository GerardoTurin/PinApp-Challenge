package trackapp.icube04backend.modules.configuration_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailHierarchyRepository;
import trackapp.icube04backend.modules.configuration_module.domain.ports.usecases.IDeleteConfigDetailHierarchyUseCase;

@Service
@RequiredArgsConstructor
public class DeleteConfigDetailHierarchyUseCase implements IDeleteConfigDetailHierarchyUseCase {

    private final IConfigDetailHierarchyRepository configDetailHierarchyRepository;


    @Override
    public void delete(Long id) {
        configDetailHierarchyRepository.delete(id);
    }
}

package trackapp.icube04backend.infrastructure.adapters.catalog_module.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.db.jpa.ModuleAppJPARepository;
import trackapp.icube04backend.infrastructure.db.model.ModuleAppEntity;
import trackapp.icube04backend.modules.catalog_module.domain.models.ModuleApp;
import trackapp.icube04backend.modules.catalog_module.domain.ports.repositories.IModuleAppRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModuleAppRepository implements IModuleAppRepository {

    private final ModuleAppJPARepository jpaRepository;

    @Override
    public List<ModuleApp> findAll() {
        return jpaRepository.findAll().stream().map(ModuleAppEntity::convertToDomain).collect(Collectors.toList());
    }

    @Override
    public ModuleApp findById(Long id) {
        return jpaRepository.findById(id).get().convertToDomain();
    }
}

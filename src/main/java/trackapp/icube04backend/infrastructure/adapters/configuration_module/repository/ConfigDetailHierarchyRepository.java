package trackapp.icube04backend.infrastructure.adapters.configuration_module.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.db.jpa.ConfigDetailHierarchyJPARepository;
import trackapp.icube04backend.infrastructure.db.model.ConfigDetailHierarchyEntity;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetailHierarchy;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailHierarchyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfigDetailHierarchyRepository implements IConfigDetailHierarchyRepository {

    private final ConfigDetailHierarchyJPARepository jpaRepository;

    @Override
    public List<ConfigDetailHierarchy> findByConfigDetailIdAndConfigDetailParentId(Long configDetailId, Long configDetailParentId) {
        return jpaRepository.findByConfigDetailIdAndConfigDetailParentId(configDetailId, configDetailParentId).stream().map(ConfigDetailHierarchyEntity::convertToDomain).toList();
    }

    @Override
    public List<ConfigDetailHierarchy> findByConfigDetailId(Long configDetailId) {
        return jpaRepository.findByConfigDetailId(configDetailId).stream().map(ConfigDetailHierarchyEntity::convertToDomain).toList();
    }

    @Override
    public ConfigDetailHierarchy save(ConfigDetailHierarchy configDetailHierarchy) {
        return jpaRepository.save(ConfigDetailHierarchyEntity.convertFromDomain(configDetailHierarchy)).convertToDomain();
    }

    @Override
    public ConfigDetailHierarchy findById(Long id) {
        return jpaRepository.findById(id).get().convertToDomain();
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }
}

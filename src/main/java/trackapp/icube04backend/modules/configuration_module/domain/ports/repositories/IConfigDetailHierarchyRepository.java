package trackapp.icube04backend.modules.configuration_module.domain.ports.repositories;

import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetailHierarchy;

import java.util.List;

public interface IConfigDetailHierarchyRepository {
    List<ConfigDetailHierarchy> findByConfigDetailIdAndConfigDetailParentId(Long configDetailId, Long configDetailParentId);

    List<ConfigDetailHierarchy> findByConfigDetailId(Long configDetailId);

    ConfigDetailHierarchy save(ConfigDetailHierarchy configDetailHierarchy);

    ConfigDetailHierarchy findById(Long id);

    void delete(Long id);
}

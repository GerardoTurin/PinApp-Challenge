package trackapp.icube04backend.modules.configuration_module.domain.ports.usecases;

import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetailHierarchy;

import java.util.List;

public interface IGetConfigDetailHierarchiesUseCase {
    List<ConfigDetailHierarchy> getByConfigDetailIdAndConfigDetailParentId(Long configDetailId, Long configDetailParentId);
}

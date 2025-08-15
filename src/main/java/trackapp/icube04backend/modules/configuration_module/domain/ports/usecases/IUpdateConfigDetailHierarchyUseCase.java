package trackapp.icube04backend.modules.configuration_module.domain.ports.usecases;

import trackapp.icube04backend.infrastructure.adapters.configuration_module.restcontrollers.dtos.requests.CustomConfigDetailHierarchyUpdateRequest;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetailHierarchy;

public interface IUpdateConfigDetailHierarchyUseCase {
    ConfigDetailHierarchy update(CustomConfigDetailHierarchyUpdateRequest request);
}

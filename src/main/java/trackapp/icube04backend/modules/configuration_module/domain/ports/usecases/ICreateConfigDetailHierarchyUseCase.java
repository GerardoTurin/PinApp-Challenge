package trackapp.icube04backend.modules.configuration_module.domain.ports.usecases;

import trackapp.icube04backend.infrastructure.adapters.configuration_module.restcontrollers.dtos.requests.CustomConfigDetailHierarchyCreateRequest;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetailHierarchy;

public interface ICreateConfigDetailHierarchyUseCase {
    ConfigDetailHierarchy create(CustomConfigDetailHierarchyCreateRequest request);
}

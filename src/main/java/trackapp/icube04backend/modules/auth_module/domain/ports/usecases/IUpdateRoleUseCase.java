package trackapp.icube04backend.modules.auth_module.domain.ports.usecases;

import trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.requests.CustomRoleUpdateRequest;
import trackapp.icube04backend.modules.auth_module.domain.models.Role;

public interface IUpdateRoleUseCase {

    Role update(CustomRoleUpdateRequest request);
}

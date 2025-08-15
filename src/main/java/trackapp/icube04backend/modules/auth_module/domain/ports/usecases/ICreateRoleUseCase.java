package trackapp.icube04backend.modules.auth_module.domain.ports.usecases;

import trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.requests.CustomRoleCreateRequest;
import trackapp.icube04backend.modules.auth_module.domain.models.Role;

public interface ICreateRoleUseCase {

    Role create(CustomRoleCreateRequest user);

}

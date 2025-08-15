package trackapp.icube04backend.modules.auth_module.domain.ports.usecases;

import trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.responses.CustomRoleResponse2;

public interface IGetRoleByIdUseCase {

    CustomRoleResponse2 getRoleById(Long id);
}

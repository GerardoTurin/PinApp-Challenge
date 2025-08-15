package trackapp.icube04backend.modules.auth_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.auth_module.domain.models.Role;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.IGetAllRolesUseCase;
import trackapp.icube04backend.modules.catalog_module.domain.ports.repositories.IRoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllRolesUseCase implements IGetAllRolesUseCase {

    private final IRoleRepository roleRepository;
    private final ISessionService sessionService;

    @Override
    public List<Role> getAllByCompanyId() {
        Long companyId = sessionService.getCompanyId();

        return roleRepository.findAll().stream()
                .filter(role -> role.getCompany().getId().equals(companyId)).toList();
    }

}

package trackapp.icube04backend.modules.track_module.application.usescases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IUserRepository;
import trackapp.icube04backend.modules.track_module.domain.models.UsersCompanies;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IUserCompaniesRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IFindUserCompaniesUseCase;

import java.util.List;


@UseCase
@RequiredArgsConstructor
public class FindUserCompaniesUseCase implements IFindUserCompaniesUseCase {

    private final IUserCompaniesRepository userCompaniesRepository;
    private final ISessionService sessionService;
    private final IUserRepository userRepository;

    @Override
    public UsersCompanies findById(Long id) {
        var currentUserCompany = userCompaniesRepository.findById(id);

        if (currentUserCompany == null) {
            throw new IllegalArgumentException("No existe la relación de usuario y compañía con id: " + id);
        }

        return currentUserCompany;
    }

    @Override
    public List<UsersCompanies> findAllByCompanyId(Long companyId) {
        return List.of();
    }

    @Override
    public List<UsersCompanies> findAllByUserId(Long userId) {
        var currentUser = userRepository.findById(userId);

        if (currentUser == null) {
            throw new IllegalArgumentException("No existe el usuario con id: " + userId);
        }

        return userCompaniesRepository.findAllByUserId(userId);
    }
}

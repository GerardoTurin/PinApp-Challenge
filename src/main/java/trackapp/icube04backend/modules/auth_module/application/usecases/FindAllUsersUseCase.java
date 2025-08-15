package trackapp.icube04backend.modules.auth_module.application.usecases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.auth_module.domain.models.User;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IUserRepository;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.IFindAllUsersUseCase;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class FindAllUsersUseCase implements IFindAllUsersUseCase {

    private final IUserRepository userRepository;
    private final ISessionService sessionService;

    @Override
    public List<User> findAllByCompanyId() {
        var companyId = sessionService.getCompanyId();

        return userRepository.findAll().stream()
                .filter(user -> user.getCompanies().stream()
                        .anyMatch(company -> company.getId().equals(companyId)))
                .collect(Collectors.toList());
    }
}


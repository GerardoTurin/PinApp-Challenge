package trackapp.icube04backend.modules.auth_module.application.usecases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IUserRepository;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.IDeleteUserUseCase;

@UseCase
@RequiredArgsConstructor
public class DeleteUserUseCase implements IDeleteUserUseCase {

    private final IUserRepository userRepository;

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}

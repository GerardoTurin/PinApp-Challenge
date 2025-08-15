package trackapp.icube04backend.modules.auth_module.application.usecases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.infrastructure.configuration.email.IEmailRepository;
import trackapp.icube04backend.modules.auth_module.domain.models.User;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IUserRepository;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.IForgotPasswordByUsernameUseCase;


@UseCase
@RequiredArgsConstructor
public class ForgotPasswordByUsernameUseCase implements IForgotPasswordByUsernameUseCase {

    private final IUserRepository userRepository;
    private final IEmailRepository repository;

    @Override
    public void execute(String username) {
        User user = userRepository.findByUsername(username) == null ? userRepository.findByEmail(username) : userRepository.findByUsername(username);

        if(user==null){
            var exceptionDetail= new RuntimeException("No se pudo encontrar usuario");
            throw new RuntimeException();
        }else{
            user.setStatus(true);
            userRepository.save(user);
            repository.sendResetPasswordEmail(user.getEmail());
        }

    }
}

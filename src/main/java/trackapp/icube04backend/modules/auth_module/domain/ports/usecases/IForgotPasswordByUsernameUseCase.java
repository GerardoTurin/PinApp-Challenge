package trackapp.icube04backend.modules.auth_module.domain.ports.usecases;

public interface IForgotPasswordByUsernameUseCase {

    void execute(String username);
}

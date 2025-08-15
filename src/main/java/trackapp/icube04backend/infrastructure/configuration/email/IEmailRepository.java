package trackapp.icube04backend.infrastructure.configuration.email;

public interface IEmailRepository {
    void sendResetPasswordEmail(String email);
    void sendPasswordEmail(String username,String email,String randomPassword);
}

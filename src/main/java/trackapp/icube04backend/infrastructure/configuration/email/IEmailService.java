package trackapp.icube04backend.infrastructure.configuration.email;

import java.util.List;

public interface IEmailService {

    void sendEmail(String from, String to, String subject, String body, List<String> ccList);

}
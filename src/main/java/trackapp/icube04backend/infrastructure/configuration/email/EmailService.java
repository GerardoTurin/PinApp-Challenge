package trackapp.icube04backend.infrastructure.configuration.email;

import com.sendgrid.*;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class EmailService implements IEmailService {
    private final String apiKey;

    @Override
    public void sendEmail(String from, String to, String subject, String body, List<String> ccList) {
        Email fromEmail = new Email(from);
        Email toEmail = new Email(to);
        Content content = new Content("text/html", body);
        Mail mail = new Mail(fromEmail, subject, toEmail, content);

        if (ccList != null) {
            for (String cc : ccList) {
                mail.personalization.get(0).addCc(new Email(cc));
            }
        }

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sg.api(request);
        } catch (IOException e) {
            throw new RuntimeException("Error al enviar correo electrónico", e);
        }
    }
}
package trackapp.icube04backend.infrastructure.configuration.email;



import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailRepository implements IEmailRepository {

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String emailFromAddress;
    @Value("${front-path}")
    private String pathBase;

    @Override
    public void sendResetPasswordEmail(String email) {
        String url=String.format("%s/reset-password?email=%s&token=%s",pathBase,email);
        String htmlContent=readEmailTemplateResetPassword(url);
        String subject = "Restablecimiento de Contraseña";
        MimeMessage message = emailSender.createMimeMessage();
        try{
            message.setFrom(emailFromAddress);
            message.setSubject(subject);
            message.setRecipients(MimeMessage.RecipientType.TO,email);
            message.setContent(htmlContent, MediaType.TEXT_HTML_VALUE);
            emailSender.send(message);
        }catch (MessagingException e){
            log.error("No es posible enviar email de reset password error: {} ",e.getMessage());
        }
    }

    @Override
    public void sendPasswordEmail(String username,String email,String randomPassword) {
        MimeMessage message = emailSender.createMimeMessage();
        String subject = "Nuevo usuario";
        String htmlContent=readEmailTemplateCreateUser(username,randomPassword);
        try{
            message.setFrom(emailFromAddress);
            message.setSubject(subject);
            message.setRecipients(MimeMessage.RecipientType.TO,email);
            message.setContent(htmlContent, MediaType.TEXT_HTML_VALUE);
            emailSender.send(message);
        }catch (MessagingException e){
            log.error("No es posible enviar email con la contraseña random: {} ",e.getMessage());
        }
    }



    private String readEmailTemplateResetPassword(String url) {
        try {
            ClassPathResource resource = new ClassPathResource("/template-email/reset-password.html");
            String templateContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            return templateContent.replace("{url}", url);
        } catch (IOException e) {
            log.error("No es posible leer template reset-password.html error: {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private String readEmailTemplateCreateUser(String user,String password) {
        try {
            ClassPathResource resource = new ClassPathResource("/template-email/create-user.html");
            String templateContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            return templateContent.replace("{user}", user).replace("{password}", password);
        } catch (IOException e) {
            log.error("No es posible leer template create-user.html error: {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
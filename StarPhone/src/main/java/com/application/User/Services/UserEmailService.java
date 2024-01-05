package com.application.User.Services;

import com.application.User.Entities.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class UserEmailService implements EmailService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String defaultMail;

    public UserEmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public boolean sendActivateEmail(User usu) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        String asunto = "Código de Activación";
        String body = "Su código de activación es: " + usu.getActivateCode();
        try {
            helper.setFrom(defaultMail);
            helper.setTo(usu.getEmail());
            helper.setSubject(asunto);
            helper.setText(body);
            this.mailSender.send(message);
        } catch (MailException | MessagingException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean sendForgotPasswordEmail(User usu, String password) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        String asunto = "Solicitud de Recuperación de Credenciales";
        String body = "Su nueva contraseña es: " + password;

        try {
            helper.setFrom(defaultMail);
            helper.setTo(usu.getEmail());
            helper.setSubject(asunto);
            helper.setText(body);
            this.mailSender.send(message);
        } catch (MailException | MessagingException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

}

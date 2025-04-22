package com.example.demo.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class CorreoService {

    @Autowired
    private JavaMailSender emailSender;

    public void enviarCorreoContrasena(String to, String username, String password) throws MessagingException {
        String subject = "Tus credenciales de acceso";
        String text = "<h3>Hola,</h3><p>Tu cuenta ha sido creada. Aquí están tus credenciales de acceso:</p>"
                    + "<p>Usuario: " + username + "</p>"
                    + "<p>Contraseña: " + password + "</p>"
                    + "<p>¡Bienvenido!</p>";

        sendEmail(to, subject, text); // Llamamos al método general que ya tienes
    }

    public void sendEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true); // `true` means it will be HTML text

        emailSender.send(message);
    }
}

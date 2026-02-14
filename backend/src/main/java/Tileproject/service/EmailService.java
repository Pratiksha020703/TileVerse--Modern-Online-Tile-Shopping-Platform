package Tileproject.service;

import Tileproject.model.ContactMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSupportMail(ContactMessage msg) {

    SimpleMailMessage mail = new SimpleMailMessage();
    mail.setFrom("mahajanpratiksha02@gmail.com");   // MUST match SMTP username
    mail.setTo("mahajanpratiksha02@gmail.com");     // testing receiver
    mail.setSubject("TileVerse Contact Message");

    mail.setText(
        "Name: " + msg.getName() +
        "\nEmail: " + msg.getEmail() +
        "\nPhone: " + msg.getPhone() +
        "\n\nMessage:\n" + msg.getMessage()
    );

    mailSender.send(mail);
}

}

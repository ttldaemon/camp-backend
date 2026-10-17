
package org.camp.camp.emails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendEmail(
            String to,
            String subject,
            String body
    ) throws MessagingException {

        MimeMessage msg = mailSender.createMimeMessage();

        MimeMessageHelper helper =
                new MimeMessageHelper(msg, "UTF-8");

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);

        // true means the body is HTML
        helper.setText(body, true);

        mailSender.send(msg);

        System.out.println("Mail submitted to SMTP server for: " + to);
    }
}

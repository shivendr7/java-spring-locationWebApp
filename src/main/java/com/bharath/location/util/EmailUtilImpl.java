package com.bharath.location.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtilImpl implements EmailUtil{

    @Autowired
    private JavaMailSender sender;

    @Override
    public void sendEmail(String toAddress, String subject, String body) {
        // mime : multipurpose internet mail extensions
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(body);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sender.send(mimeMessage);
    }
}

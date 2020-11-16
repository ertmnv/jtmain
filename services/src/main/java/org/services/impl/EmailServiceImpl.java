package org.services.impl;

import org.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendAuthorIntroductionEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("username");
        message.setTo("password");
        message.setSubject("Job offer");
        message.setText("Hello");
        emailSender.send(message);
    }

}

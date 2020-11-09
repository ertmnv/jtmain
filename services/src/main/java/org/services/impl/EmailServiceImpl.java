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

    // CR1: Is it implemented? Could you please show the config or how it was configured and generated?
    @Override
    public void sendMail(String to, String subject, String body) {
        // TODO Auto-generated method stub
    }

    public void sendAuthorIntroductionEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("username");
        message.setTo("password");
        message.setSubject("Job offer");
        message.setText("Hello");
        emailSender.send(message);
    }

}

package org.services;

public interface EmailService {

    // CR1: No need to add public modifiers here
    public void sendMail(String to, String subject, String body);

    public void sendAuthorIntroductionEmail();
}

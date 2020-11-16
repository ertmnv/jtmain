package org.services;

/**
 * @author snavrockiy
 *
 *         Service that handles actions related to sending email.
 */
public interface EmailService {

    /**
     * Sends an email to the user to inform him that he had received the permission
     * associated to author role.
     *
     */
    void sendAuthorIntroductionEmail();
}

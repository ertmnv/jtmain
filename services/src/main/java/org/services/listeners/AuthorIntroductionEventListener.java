package org.services.listeners;

import org.services.events.AuthorIntroductionEvent;
import org.services.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author snavrockiy
 *
 *         Listener which handle
 *         {@link org.services.events.AuthorIntroductionEvent} and send email to
 *         user that informs him that he is registered as author.
 *
 */
@Component
public class AuthorIntroductionEventListener {

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onApplicationEvent(final AuthorIntroductionEvent event) {
        emailServiceImpl.sendAuthorIntroductionEmail();
    }

}

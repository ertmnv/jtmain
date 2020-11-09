package org.services.listeners;

import org.services.events.AuthorIntroductionEvent;
import org.services.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;



@Component
public class AuthorIntroductionEventListener {

    @Autowired
    EmailServiceImpl emailServiceImpl;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onApplicationEvent(AuthorIntroductionEvent event) {
        emailServiceImpl.sendAuthorIntroductionEmail();
    }

}

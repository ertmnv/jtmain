package com.iverbs.jtmain.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.iverbs.jtmain.events.AuthorIntroductionEvent;
import com.iverbs.jtmain.service.impl.EmailServiceImpl;

@Component
public class AuthorIntroductionEventListener {

    @Autowired
    EmailServiceImpl emailServiceImpl;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onApplicationEvent(AuthorIntroductionEvent event) {
        emailServiceImpl.sendAuthorIntroductionEmail();
    }

}

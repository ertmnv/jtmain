package org.services.events;

import org.springframework.context.ApplicationEvent;

/**
 * @author snavrockiy
 *
 *         Event that made listener to send email that informs user that he is registered as author.
 *
 */
public class AuthorIntroductionEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    public AuthorIntroductionEvent(final Object source) {
        super(source);
    }

}

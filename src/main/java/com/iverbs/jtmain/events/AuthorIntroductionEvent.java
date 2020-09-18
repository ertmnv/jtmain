package com.iverbs.jtmain.events;

import org.springframework.context.ApplicationEvent;

public class AuthorIntroductionEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    public AuthorIntroductionEvent(Object source) {
        super(source);
    }

}

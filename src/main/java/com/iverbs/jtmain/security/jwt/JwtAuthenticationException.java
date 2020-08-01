package com.iverbs.jtmain.security.jwt;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
        // TODO Auto-generated constructor stub
    }
    
    public JwtAuthenticationException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

}

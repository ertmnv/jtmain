package org.security.services.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * @author snavrockiy
 *
 *         Exception is thrown when validation of JWT in
 *         {@link JwtTokenProvider#validateToken(String)} token is not finished
 *         successfully.
 *
 *
 */
public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(final String msg, final Throwable t) {
        super(msg, t);
    }

    public JwtAuthenticationException(final String msg) {
        super(msg);
    }

}

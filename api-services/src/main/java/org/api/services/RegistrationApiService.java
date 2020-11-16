package org.api.services;

import org.springframework.http.ResponseEntity;

/**
 * @author snavrockiy
 *
 *         Api-service that handles actions related to author registration.
 */
public interface RegistrationApiService {

    /**
     * Registers user as author.
     *
     * @param username the name of the user who should be registered as author
     * @return {@code ResponseEntity} with body that contains token which was
     *         updated according to author role and the {@linkplain HttpStatus#OK
     *         OK} status
     */
    ResponseEntity registerAuthor(String username);

}

package org.api.services;


import org.db.dto.AuthenticationRequestDto;
import org.db.dto.RegistrationRequestDto;
import org.springframework.http.ResponseEntity;


/**
 * @author snavrockiy
 *
 *         Api-service that handles actions related to authentication.
 */
public interface AuthenticationApiService {

    /**
     * Performs authentication.
     *
     * @param requestDto credentials of the user to authenticate
     * @return {@code ResponseEntity} with body that contains name of authenticated
     *         user and JWT token and the {@linkplain HttpStatus#OK OK} status
     */
    ResponseEntity login(AuthenticationRequestDto requestDto);

    /**
     * Registers user.
     *
     * @param requestDto credentials of the user to register
     * @return {@code ResponseEntity} with body that contains registered user and
     *         the {@linkplain HttpStatus#OK OK} status
     */
    ResponseEntity register(RegistrationRequestDto requestDto);

}

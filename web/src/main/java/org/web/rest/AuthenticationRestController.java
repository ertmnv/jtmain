package org.web.rest;

import javax.validation.Valid;

import org.api.services.AuthenticationApiService;
import org.db.dto.AuthenticationRequestDto;
import org.db.dto.RegistrationRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author snavrockiy
 *
 *         Controller that handles actions related to authentication. Per rules
 *         in {@link org.security.config.SecurityConfig}, authentication is not
 *         required for all requests.
 */
@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestController {

    @Autowired
    private AuthenticationApiService authenticationApiService;

    /**
     * Performs authentication.
     *
     * @param requestDto credentials of the user to authenticate
     * @return {@code ResponseEntity} with body that contains name of authenticated user
     *         and JWT token and the {@linkplain HttpStatus#OK OK} status
     */
    @PostMapping("login")
    public ResponseEntity login(@RequestBody final AuthenticationRequestDto requestDto) {
        return authenticationApiService.login(requestDto);
    }

    /**
     * Registers user.
     *
     * @param requestDto credentials of the user to register
     * @return {@code ResponseEntity} with body that contains registered user
     *         and the {@linkplain HttpStatus#OK OK} status
     */
    @PostMapping("register")
    public ResponseEntity register(@Valid @RequestBody final RegistrationRequestDto requestDto) {
        return authenticationApiService.register(requestDto);
    }

}

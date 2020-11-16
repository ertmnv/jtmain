package org.web.rest;

import org.api.services.RegistrationApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author snavrockiy
 *
 *         Controller that handles actions related to author registration. Per
 *         rules in {@link org.security.config.SecurityConfig}, authentication
 *         is required for all requests.
 */
@Controller
@RequestMapping(value = "/api/v1/author/")
public class AuthorRegistrationController implements RegistrationApiService {

    @Autowired
    private RegistrationApiService registrationApiService;

    /**
     * Registers user as author.
     *
     * @param username the name of the user who should be registered as author
     * @return {@code ResponseEntity} with body that contains token which was
     *         updated according to author role and the {@linkplain HttpStatus#OK
     *         OK} status
     */
    @Override
    @GetMapping("/registration/{username}")
    public ResponseEntity registerAuthor(@PathVariable final String username) {
        return registrationApiService.registerAuthor(username);
    }
}

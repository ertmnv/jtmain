package org.web.rest;

import org.api.services.RegistrationApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/v1/author/")
public class AuthorRegistrationController implements RegistrationApiService {

    @Autowired
    private RegistrationApiService registrationApiService;

    @Override
    @GetMapping("/registration/{username}")
    public ResponseEntity registerAuthor(@PathVariable final String username) {
        return registrationApiService.registerAuthor(username);
    }
}

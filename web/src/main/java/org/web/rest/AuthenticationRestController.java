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

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestController {

    @Autowired
    private AuthenticationApiService authenticationApiService;
    
    @PostMapping("login")
    public ResponseEntity login(@RequestBody final AuthenticationRequestDto requestDto) {
        return authenticationApiService.login(requestDto);
    }

    
    @PostMapping("register")
    public ResponseEntity register(@Valid @RequestBody final RegistrationRequestDto requestDto) {
        return authenticationApiService.register(requestDto);
    }

}

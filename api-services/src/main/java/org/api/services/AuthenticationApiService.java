package org.api.services;

import javax.validation.Valid;

import org.db.dto.AuthenticationRequestDto;
import org.db.dto.RegistrationRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationApiService {

    ResponseEntity login(AuthenticationRequestDto requestDto);

    ResponseEntity register(RegistrationRequestDto requestDto);

}
package org.api.services;

import org.springframework.http.ResponseEntity;

public interface RegistrationApiService {

    ResponseEntity registerAuthor(String username);

}
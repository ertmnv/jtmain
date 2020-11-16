package org.api.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.api.services.RegistrationApiService;
import org.db.model.Author;
import org.security.services.jwt.JwtTokenProvider;
import org.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationApiServiceImpl implements RegistrationApiService {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public ResponseEntity registerAuthor(final String username) {
        Author author = authorService.register(username);
        String token = jwtTokenProvider.createToken(username, author.getUser());
        Map<Object, Object> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

}

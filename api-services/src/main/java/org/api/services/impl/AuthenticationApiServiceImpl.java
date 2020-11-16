package org.api.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.api.services.AuthenticationApiService;
import org.db.dto.AuthenticationRequestDto;
import org.db.dto.RegistrationRequestDto;
import org.db.model.User;
import org.security.services.jwt.JwtTokenProvider;
import org.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationApiServiceImpl implements AuthenticationApiService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationApiServiceImpl(final AuthenticationManager authenticationManager,
            final JwtTokenProvider jwtTokenProvider, final UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @Override
    @Transactional
    public ResponseEntity login(final AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }
            String token = jwtTokenProvider.createToken(username, user);
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    @Transactional
    public ResponseEntity register(final RegistrationRequestDto requestDto) {
        User user = requestDto.toUser();
        // CR1 a bit strange code. You have created a user but didn't return it back.
        userService.register(user);
        Map<Object, Object> response = new HashMap<>();
        response.put("info", requestDto);
        return ResponseEntity.ok(response);
    }

}

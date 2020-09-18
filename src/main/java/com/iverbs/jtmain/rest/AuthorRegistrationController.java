package com.iverbs.jtmain.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iverbs.jtmain.model.Author;
import com.iverbs.jtmain.security.jwt.JwtTokenProvider;
import com.iverbs.jtmain.service.AuthorService;

@Controller
@RequestMapping(value = "/api/v1/author/")
public class AuthorRegistrationController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/registration/{username}")
    public ResponseEntity registerAuthor(@PathVariable String username) {
        Author author = authorService.register(username);
        String token = jwtTokenProvider.createToken(username, author.getUser());
        Map<Object, Object> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}

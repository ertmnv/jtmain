package com.iverbs.jtmain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iverbs.jtmain.events.AuthorIntroductionEvent;
import com.iverbs.jtmain.model.Author;
import com.iverbs.jtmain.model.User;
import com.iverbs.jtmain.repository.AuthorRepository;
import com.iverbs.jtmain.service.AuthorService;
import com.iverbs.jtmain.service.UserService;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional
    public Author register(String username) {
        User user = userService.findByUsername(username);
        Author author = new Author();
        author.setUser(user);
        Author persistedAuthor = authorRepository.save(author);
        applicationEventPublisher.publishEvent(new AuthorIntroductionEvent("Author is created successfully"));
        return persistedAuthor;
    }

    @Override
    public Author findById(Long id) {
        System.out.println("user was retrived from database");
        return authorRepository.findById(id);
    }

}

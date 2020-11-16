package org.services.impl;

import org.db.model.Author;
import org.db.model.User;
import org.db.repository.AuthorRepository;
import org.services.AuthorService;
import org.services.UserService;
import org.services.events.AuthorIntroductionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Transactional(propagation = Propagation.REQUIRED)
    public Author register(final String username) {
        User user = userService.findByUsername(username);
        Author author = new Author();
        author.setUser(user);
        Author persistedAuthor = authorRepository.save(author);
        applicationEventPublisher.publishEvent(new AuthorIntroductionEvent("Author is created successfully"));
        return persistedAuthor;
    }

    public Author findById(final Long id) {
        System.out.println("user was retrived from database");
        return authorRepository.findById(id);
    }

}

package com.iverbs.jtmain.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iverbs.jtmain.model.Author;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    @Autowired
    EntityManager em;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Author save(Author author) {
        if (author.getId() == null) {
            em.persist(author);
        } else {
            em.merge(author);
        }
        em.refresh(author.getUser());
        return author;
    }

    @Override
    public Author findById(Long id) {
        Author author = em.find(Author.class, id);
        return author;
    }

}

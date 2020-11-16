package org.db.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.db.model.Author;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    @Autowired
    private EntityManager em;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Author save(final Author author) {
        if (author.getId() == null) {
            em.persist(author);
        } else {
            em.merge(author);
        }
        em.refresh(author.getUser());
        return author;
    }

    @Override
    public Author findById(final Long id) {
        Author author = em.find(Author.class, id);
        return author;
    }

}

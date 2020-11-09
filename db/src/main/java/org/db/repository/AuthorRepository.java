package org.db.repository;

import org.db.model.Author;

public interface AuthorRepository {

    Author save(Author author);

    Author findById(Long id);
}

package org.services;

import org.db.model.Author;

public interface AuthorService {

    Author register(String username);

    Author findById(Long id);

}

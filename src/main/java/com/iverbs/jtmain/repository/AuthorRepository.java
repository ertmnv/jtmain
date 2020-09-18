package com.iverbs.jtmain.repository;

import com.iverbs.jtmain.model.Author;

public interface AuthorRepository {

    Author save(Author author);

    Author findById(Long id);
}

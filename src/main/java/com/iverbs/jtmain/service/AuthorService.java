package com.iverbs.jtmain.service;

import com.iverbs.jtmain.model.Author;

public interface AuthorService {

    Author register(String username);

    Author findById(Long id);

}

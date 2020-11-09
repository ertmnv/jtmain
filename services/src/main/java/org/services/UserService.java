package org.services;

import java.util.List;

import org.db.model.User;


public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(long id);
}

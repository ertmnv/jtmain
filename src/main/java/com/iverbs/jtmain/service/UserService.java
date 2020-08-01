package com.iverbs.jtmain.service;

import java.util.List;

import com.iverbs.jtmain.model.User;

public interface UserService {
    
    User register(User user);
    
    List<User> getAll();
    
    User findByUsername(String username);
    
    User findById(Long id);
    
    void delete(long id);
}

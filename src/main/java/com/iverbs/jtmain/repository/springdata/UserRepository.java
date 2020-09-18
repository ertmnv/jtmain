package com.iverbs.jtmain.repository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iverbs.jtmain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
}

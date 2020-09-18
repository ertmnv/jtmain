package com.iverbs.jtmain.repository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iverbs.jtmain.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

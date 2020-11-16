package org.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.db.model.Role;
import org.db.model.Status;
import org.db.model.User;
import org.db.repository.springdata.RoleRepository;
import org.db.repository.springdata.UserRepository;
import org.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User register(final User user) {
        // TODO Auto-generated method stub
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<Role>();
        userRoles.add(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        User registredUser = userRepository.save(user);
        return registredUser;
    }

    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        List<User> result = userRepository.findAll();
        return result;
    }

    @Override
    public User findByUsername(final String username) {
        // TODO Auto-generated method stub
        User result = userRepository.findByUsername(username);
        return result;
    }

    @Override
    public User findById(final Long id) {
        // TODO Auto-generated method stub
        User result = userRepository.findById(id).orElse(null);
        if (result == null) {
            return null;
        }
        return result;
    }

    @Override
    public void delete(final long id) {
        // TODO Auto-generated method stub
        userRepository.deleteById(id);
    }

}

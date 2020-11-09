package org.security.services;

import org.db.model.User;
import org.security.services.jwt.JwtUser;
import org.security.services.jwt.JwtUserFactory;
import org.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username" + username + "not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }

}

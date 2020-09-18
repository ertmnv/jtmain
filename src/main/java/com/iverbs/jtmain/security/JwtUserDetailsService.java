package com.iverbs.jtmain.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iverbs.jtmain.model.User;
import com.iverbs.jtmain.security.jwt.JwtUser;
import com.iverbs.jtmain.security.jwt.JwtUserFactory;
import com.iverbs.jtmain.service.UserService;

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

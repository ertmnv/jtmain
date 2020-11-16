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

/**
 * @author snavrockiy
 *
 *         This service is used by the DaoAuthenticationProvider to load details
 *         about the user during authentication. Inside loadUserByUsername
 *         {@link UserService#findByUsername } is invoked and User is converted
 *         to JwtUser that implements UserDetails. JwtUser is used in order to
 *         decouple User from UserDetails.
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username" + username + "not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }

}

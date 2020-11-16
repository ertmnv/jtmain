package org.security.services.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.db.model.Role;
import org.db.model.Status;
import org.db.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(final User user) {
        JwtUser jwtUser = new JwtUser();

        jwtUser.setId(user.getId());
        jwtUser.setUsername(user.getUsername());
        jwtUser.setFirstName(user.getFirstName());
        jwtUser.setLastName(user.getLastName());
        jwtUser.setEmail(user.getEmail());
        jwtUser.setPassword(user.getPassword());
        jwtUser.setAuthorities(mapToGrantedAuthorities(new ArrayList<>(user.getRoles())));
        jwtUser.setEnabled(user.getStatus().equals(Status.ACTIVE));
        jwtUser.setLastPasswordResetDate(user.getUpdated());
        /*
         * return new JwtUser(user.getId(), user.getUsername(), user.getFirstName(),
         * user.getLastName(), user.getEmail(), user.getPassword(),
         * mapToGrantedAuthorities(new ArrayList<>(user.getRoles())),
         * user.getStatus().equals(Status.ACTIVE), user.getUpdated());
         */
        return jwtUser;
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(final List<Role> userRoles) {
        return userRoles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}

package org.security.services.jwt;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author snavrockiy
 *
 *         Is used in authentication process. Provided by
 *         {@link org.security.services.JwtUserDetailsService} for
 *         DaoAuthenticationProvider.
 *
 */
public class JwtUser implements UserDetails {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private boolean enabled;

    private Date lastPasswordResetDate;

    private Collection<? extends GrantedAuthority> authorities;

    /*
     * public JwtUser(final Long id, final String username, final String firstName,
     * final String lastName, final String email, final String password, final
     * Collection<? extends GrantedAuthority> authorities, final boolean enabled,
     * final Date lastPasswordResetDate) { this.id = id; this.username = username;
     * this.firstName = firstName; this.lastName = lastName; this.email = email;
     * this.password = password; this.authorities = authorities; this.enabled =
     * enabled; this.lastPasswordResetDate = lastPasswordResetDate; }
     */

    public JwtUser() {
        super();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public void setLastPasswordResetDate(final Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public void setAuthorities(final Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

}

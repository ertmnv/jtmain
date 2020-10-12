package com.iverbs.jtmain.dto;

import com.iverbs.jtmain.model.User;
import com.iverbs.jtmain.shared.UniqueUsername;

// CR1: you have lombok in your project. Why don't you use it for eg here? (See @Data annotation)
public class RegistrationRequestDto {

    @UniqueUsername
    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private String email;

    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

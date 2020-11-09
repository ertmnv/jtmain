package org.db.dto;


import org.db.model.User;
import org.db.shared.UniqueUsername;

import lombok.Data;
import lombok.NoArgsConstructor;

// CR1: you have lombok in your project. Why don't you use it for eg here? (See @Data annotation)
@Data
@NoArgsConstructor
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

}

package org.db.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * DTO is used to receive login and password from client.
 */
@Data
@NoArgsConstructor
public class AuthenticationRequestDto {

    private String username;

    private String password;

}

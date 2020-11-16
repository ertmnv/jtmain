package org.security.services.jwt;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.db.model.Author;
import org.db.model.Role;
import org.db.model.Student;
import org.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author snavrockiy
 *
 *         Class handles action related to creation, validation, parsing of JWT
 *         token. Provides Authentication for {@link JwtTokenFilter} which put
 *         Authentication into
 *         org.springframework.security.core.context.SecurityContext.
 */
@Component
public class JwtTokenProvider {

    private static final int TOKE_PREFIX = 7;

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    /**
     * Creates JWT token. Puts information into the token whether the user is a
     * student or an author.
     *
     * @param username name of user for which the token is being generated
     * @param user     the user for which the token is being generated
     * @return JWT token
     */
    public String createToken(final String username, final User user) {
        List<Role> roles = user.getRoles();
        Author author = user.getAuthor();
        Student student = user.getStudent();
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", getRoleNames(roles));
        if (author != null) {
            claims.put("authorId", author.getId());
        }
        if (student != null) {
            claims.put("studentId", student.getId());
        }
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    /**
     * @param token the token the name of user will be extracted from
     * @return Authentication that will be put in
     *         org.springframework.security.core.context.SecurityContext by
     *         {@link JwtTokenFilter}
     */
    public Authentication getAuthentication(final String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * Retrieves name of the user.
     *
     * @param token the token the name of user will be extracted from
     * @return name of the user
     */
    public String getUsername(final String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Validates token.
     *
     * @param token the token that will be validated
     * @return whether token valid or not.
     */
    public boolean validateToken(final String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expire or invalid");
        }
    }

    /**
     * Obtains JW token from the header of request.
     *
     * @param req the request from the header of which the token will be extracted.
     * @return JWT token
     */
    public String resolveToken(final HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(TOKE_PREFIX, bearerToken.length());
        }
        return null;
    }

    /**
     * Converts List<Role> to List<String> where each element is name of the Role.
     *
     * @param userRoles to convert
     * @return list of roles
     */
    private List<String> getRoleNames(final List<Role> userRoles) {
        List<String> result = new ArrayList<>();
        userRoles.forEach(role -> {
            result.add(role.getName());
        });
        return result;
    }
}

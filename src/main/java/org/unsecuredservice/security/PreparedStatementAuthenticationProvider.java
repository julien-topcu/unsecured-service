package org.unsecuredservice.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.unsecuredservice.model.User;

@Component
@Profile("safer")
public class PreparedStatementAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String name = authentication.getName();
        final String password = authentication.getCredentials().toString();
        final List<User> users = jdbcTemplate.query((Connection connection) -> {
            PreparedStatement statement = connection.prepareStatement("Select * from USERS where username=? and password=?");
            statement.setString(1, name);
            statement.setString(2, password);
            return statement;
        }, BeanPropertyRowMapper.newInstance(User.class));

        if (users.isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("Wrong credentials");
        }
        final User user = users.get(0);//bad practice you shouldn't do that
        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), null);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class
        );
    }
}

package org.unsecuredservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.unsecuredservice.model.User;

@Component
@Profile("unsecured")
public class RawSQLAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            final String name = authentication.getName();
            final String password = authentication.getCredentials().toString();
            User user = jdbcTemplate
                    .queryForObject(
                            "Select * from USERS where username='" + name + "' and password='" + password + "'",
                            BeanPropertyRowMapper.newInstance(User.class)
                    );
            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), null);

        } catch (EmptyResultDataAccessException e) {
            throw new BadCredentialsException("Who are you????", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class
        );
    }
}

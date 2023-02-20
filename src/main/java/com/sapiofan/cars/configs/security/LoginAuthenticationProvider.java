package com.sapiofan.cars.configs.security;

import com.sapiofan.cars.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Lazy
    private CustomUserDetailsService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        User retrievedUser = userService.login(username, password);
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(retrievedUser.getRole().toString());
        return new UsernamePasswordAuthenticationToken(retrievedUser.getId(), password, Collections.singleton(grantedAuthority));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

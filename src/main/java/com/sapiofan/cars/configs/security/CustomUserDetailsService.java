package com.sapiofan.cars.configs.security;

import com.sapiofan.cars.entities.Role;
import com.sapiofan.cars.entities.User;
import com.sapiofan.cars.repos.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userRepository.findByPhone(phone);
        if (user == null) {
            log.error("User with such phone wasn't found: " + phone);
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(user);
    }

    public String signUp(String phone, String password, String address, String name, String surname) {
        User user = userRepository.findByPhone(phone);

        if (user != null) {
            log.warn("User with such phone already exists" + phone);
            return "User with such phone already exists";
        }

        user = new User();
        user.setPhone(phone);
        user.setAddress(address);
        user.setFirst_name(name);
        user.setSurname(surname);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setRole(Role.CUSTOMER);
        userRepository.save(user);

        return "";
    }

    public String signIn(String phone, String password, HttpServletRequest request) {
        User user = userRepository.findByPhone(phone);

        if (user == null) {
            log.warn("Wrong phone was inputted: " + phone);
            return "Email was wrong";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "Password was wrong";
        }

        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(user.getPhone(), password);
        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = request.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

        return "";
    }

    public User getUserByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }
}

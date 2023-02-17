package com.sapiofan.cars.controllers;

import com.sapiofan.cars.configs.security.CustomUserDetailsService;
import com.sapiofan.cars.entities.Car;
import com.sapiofan.cars.services.CarsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private CarsServiceImpl carsService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carsService.getAllCars();
    }

    @GetMapping("/car")
    public String getCar() {
        return "String";
    }


    @PostMapping(value = "/registration")
    public String registration(@RequestParam("phone") String phone,
                               @RequestParam("password") String password,
                               @RequestParam("address") String address,
                               @RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               HttpServletRequest request) {

        String result = userDetailsService.signUp(phone, password, address, name, surname);
        if (!result.isEmpty()) {
            return result;
        }

        return login(phone, password, request);
    }

    @PostMapping("/login")
    public String login(@RequestParam("phone") String phone,
                        @RequestParam("password") String password,
                        HttpServletRequest request) {

        String res = userDetailsService.signIn(phone, password, request);
        log.warn(SecurityContextHolder.getContext().getAuthentication().getName());

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping(value = "/isLoggedIn")
    public boolean userIsLoggedIn() {
        log.warn(SecurityContextHolder.getContext().getAuthentication()+"");
        return SecurityContextHolder.getContext().getAuthentication() != null
                && !SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser");
    }

    @PostMapping(value = "/logout")
    public Authentication logout() {
        log.warn(SecurityContextHolder.getContext().getAuthentication().getName());
        SecurityContextHolder.getContext().setAuthentication(null);
        log.warn(SecurityContextHolder.getContext().getAuthentication()+"");
        return SecurityContextHolder.getContext().getAuthentication();
    }
}

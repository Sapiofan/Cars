package com.sapiofan.cars.controllers;

import com.sapiofan.cars.configs.security.CustomUserDetailsService;
import com.sapiofan.cars.entities.Car;
import com.sapiofan.cars.entities.Contract;
import com.sapiofan.cars.entities.User;
import com.sapiofan.cars.services.CarsServiceImpl;
import com.sapiofan.cars.services.ContractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MainController {

    @Autowired
    private CarsServiceImpl carsService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private ContractServiceImpl contractService;

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carsService.getAllCars();
    }

    @GetMapping("/car")
    public Car getCar(@RequestParam("id") Long id) {
        return carsService.getCar(id);
    }

    @PostMapping("/booking")
    public void booking(@RequestParam("start_date") Date start, @RequestParam("start_date") Date end,
                        @RequestParam("preferences") Set<String> preferences, @RequestParam("car") Long carId,
                        @RequestParam("end_price") Double end_price, Authentication authentication,
                        HttpServletResponse response) {
        User user = userDetailsService.getUserByPhone(authentication.getName());
        Contract contract = contractService.createContract(start, end, preferences,
                carsService.getCar(carId), end_price, user);

        if(contract == null) {
            response.setStatus(422);
            return;
        }
        user.setRent_number(user.getRent_number() + 1);
        userDetailsService.save(user);

        response.setStatus(201);
    }


    @PostMapping(value = "/registration")
    public void registration(@RequestParam("phone") String phone,
                             @RequestParam("password") String password,
                             @RequestParam("address") String address,
                             @RequestParam("name") String name,
                             @RequestParam("surname") String surname,
                             HttpServletRequest request, HttpServletResponse response) {

        String result = userDetailsService.signUp(phone, password, address, name, surname);
        if (!result.isEmpty()) {
            response.setStatus(422);
        } else {
            response.setStatus(201);
        }

        login(phone, password, request);
    }

    @PostMapping("/login")
    public void login(@RequestParam("phone") String phone,
                      @RequestParam("password") String password,
                      HttpServletRequest request) {
        userDetailsService.signIn(phone, password, request);
    }

    @GetMapping(value = "/isLoggedIn")
    public boolean userIsLoggedIn() {
        log.warn(SecurityContextHolder.getContext().getAuthentication().getName() + "");
        return SecurityContextHolder.getContext().getAuthentication() != null
                && !SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser");
    }

    @PostMapping(value = "/logout")
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @GetMapping("/user")
    public User getUser(Authentication authentication) {
        if (authentication != null) {
            return userDetailsService.getUserByPhone(authentication.getName());
        }
        return null;
    }

    @GetMapping("/history")
    public List<Contract> getUserHistory(Authentication authentication) {
        if (authentication != null) {
            return contractService.getContractsOfUser(userDetailsService.getUserByPhone(authentication.getName()));
        }

        return null;
    }
}

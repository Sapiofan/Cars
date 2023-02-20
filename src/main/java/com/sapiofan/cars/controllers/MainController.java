package com.sapiofan.cars.controllers;

import com.sapiofan.cars.configs.security.CustomUserDetails;
import com.sapiofan.cars.configs.security.CustomUserDetailsService;
import com.sapiofan.cars.configs.security.JwtResponse;
import com.sapiofan.cars.configs.security.JwtUtils;
import com.sapiofan.cars.entities.Car;
import com.sapiofan.cars.entities.Contract;
import com.sapiofan.cars.entities.User;
import com.sapiofan.cars.entities.ui.UserUI;
import com.sapiofan.cars.services.CarsServiceImpl;
import com.sapiofan.cars.services.ContractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MainController {

    @Autowired
    private CarsServiceImpl carsService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private ContractServiceImpl contractService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

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
    public void booking(@RequestParam("start_date") Date start, @RequestParam("end_date") Date end,
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

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody UserUI userUI) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userUI.getPhone(), userUI.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User user1 = userDetailsService.getUserByPhone(user.getPhone());
        if (user1 != null) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: User is already existed!");
        }
        String initialPassword = user.getPassword();
        user.setPassword(encoder.encode(user.getPassword()));
        userDetailsService.save(user);

        return authenticateUser(new UserUI(user.getPhone(), initialPassword));
    }

//    @GetMapping(value = "/isLoggedIn")
//    public boolean userIsLoggedIn(Authentication authentication) {
//        if(authentication != null)
//            log.warn(authentication.getName() + "");
//        return authentication != null
//                && !authentication.getName().equals("anonymousUser");
//    }

    @GetMapping(value = "/user")
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

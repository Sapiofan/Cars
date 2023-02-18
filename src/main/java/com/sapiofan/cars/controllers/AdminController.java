package com.sapiofan.cars.controllers;

import com.sapiofan.cars.configs.security.CustomUserDetailsService;
import com.sapiofan.cars.entities.Car;
import com.sapiofan.cars.entities.Contract;
import com.sapiofan.cars.entities.User;
import com.sapiofan.cars.services.CarsServiceImpl;
import com.sapiofan.cars.services.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
    @Autowired
    private CarsServiceImpl carsService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private ContractServiceImpl contractService;

    @GetMapping("/getAllHistory")
    public List<Contract> contracts() {
        return contractService.getFullHistory();
    }

    @GetMapping("/getHistoryOfCar")
    public List<Contract> contractsOfCar(Long id) {
        return contractService.getCarHistory(carsService.getCar(id));
    }

    @PostMapping("/addCarParams")
    public void addCarParams(@RequestParam("name") String name, @RequestParam("brand") String brand,
                             @RequestParam("type") String type, @RequestParam("year") Integer year,
                             @RequestParam("price") Integer price, @RequestParam("speed") Integer speed,
                             @RequestParam("engine_speed") Integer engine_speed, @RequestParam("gearbox") String gearbox,
                             @RequestParam("seats") Byte seats, @RequestParam("fuel_consumption") Double fuel_consumption,
                             @RequestParam("fuel_type") String fuel_type, @RequestParam("pledge") Integer pledge,
                             @RequestParam("image") String image, HttpServletResponse response) {
        addCarObject(new Car(name, brand, type, year, price, engine_speed, speed, gearbox, seats, fuel_consumption,
                fuel_type, pledge, image), response);
    }

    @PostMapping("/addCar")
    public void addCarObject(Car car, HttpServletResponse response) {
        Car newCar = carsService.add(car);
        if(newCar == null) {
            response.setStatus(422);
            return;
        }
        response.setStatus(201);
    }

    @PostMapping("/removeCar")
    public void removeCar(@RequestParam("id") Long cardId, HttpServletResponse response) {
        if(carsService.removeCar(carsService.getCar(cardId))) {
            response.setStatus(422);
            return;
        }
        response.setStatus(201);
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userDetailsService.getAllUsers();
    }

    @PostMapping("/setForfeit")
    public void setForfeit(@RequestParam("id") Long userId, @RequestParam("forfeit") Double forfeit,
                           HttpServletResponse response) {
        User user = userDetailsService.getUserById(userId);
        if(user != null) {
            user.setForfeit(forfeit);
            userDetailsService.save(user);
            response.setStatus(201);
            return;
        }
        response.setStatus(422);
    }
}

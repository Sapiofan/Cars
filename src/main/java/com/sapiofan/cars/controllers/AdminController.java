package com.sapiofan.cars.controllers;

import com.sapiofan.cars.configs.security.CustomUserDetailsService;
import com.sapiofan.cars.entities.Car;
import com.sapiofan.cars.entities.Contract;
import com.sapiofan.cars.entities.User;
import com.sapiofan.cars.services.CarsServiceImpl;
import com.sapiofan.cars.services.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000")
public class AdminController {
    @Autowired
    private CarsServiceImpl carsService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private ContractServiceImpl contractService;

    @GetMapping("/admin/getAllHistory")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Contract> contracts() {
        return contractService.getFullHistory();
    }

    @GetMapping("/admin/getHistoryOfCar")
    public List<Contract> contractsOfCar(Long id) {
        return contractService.getCarHistory(carsService.getCar(id));
    }

    @GetMapping("/admin/getTimeHistoryStart")
    public List<Contract> contractsTimeHistoryByStartDate(Date start, Date end) {
        return contractService.getTimeHistoryByStartDate(start, end);
    }

    @GetMapping("/admin/getTimeHistoryEnd")
    public List<Contract> contractsTimeHistoryByEndDate(Date start, Date end) {
        return contractService.getTimeHistoryByEndDate(start, end);
    }

    @PostMapping("/admin/addCarParams")
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

    @PostMapping("/admin/addCar")
    public void addCarObject(Car car, HttpServletResponse response) {
        Car newCar = carsService.add(car);
        if(newCar == null) {
            response.setStatus(422);
            return;
        }
        response.setStatus(201);
    }

    @PostMapping("/admin/removeCar")
    public void removeCar(@RequestParam("id") Long cardId, HttpServletResponse response) {
        if(carsService.removeCar(carsService.getCar(cardId))) {
            response.setStatus(422);
            return;
        }
        response.setStatus(201);
    }

    @GetMapping("/admin/getUsers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getUsers() {
        return userDetailsService.getAllUsers();
    }

    @PostMapping("/admin/setForfeit")
    public void setForfeit(@RequestParam("id") Long userId, @RequestParam("forfeit") Double forfeit,
                           HttpServletResponse response) {
        User user = userDetailsService.getUserById(userId);
        if(user != null && forfeit >= 0) {
            user.setForfeit(forfeit);
            userDetailsService.save(user);
            response.setStatus(201);
            return;
        }
        response.setStatus(422);
    }
}

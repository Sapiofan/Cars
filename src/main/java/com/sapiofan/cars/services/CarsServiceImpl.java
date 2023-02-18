package com.sapiofan.cars.services;

import com.sapiofan.cars.entities.Car;
import com.sapiofan.cars.repos.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarsServiceImpl {

    @Autowired
    private CarRepo carRepo;

    public List<Car> getAllCars() {
        return carRepo.findAll();
    }

    public Car getCar(Long id) {
        Optional<Car> car = carRepo.findById(id);
        if (car.isEmpty()) {
            return null;
        }
        return car.get();
    }

    public Car add(Car car) {
        return carRepo.save(car);
    }

    public boolean removeCar(Car car) {
        if(car != null) {
            carRepo.delete(car);
            return true;
        }

        return false;
    }
}

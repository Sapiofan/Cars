package com.sapiofan.cars.repos;

import com.sapiofan.cars.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepo extends JpaRepository<Car, Long> {

}

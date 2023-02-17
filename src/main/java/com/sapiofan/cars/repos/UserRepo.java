package com.sapiofan.cars.repos;

import com.sapiofan.cars.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByPhone(String phone);
}

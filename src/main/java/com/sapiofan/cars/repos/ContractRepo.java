package com.sapiofan.cars.repos;

import com.sapiofan.cars.entities.Contract;
import com.sapiofan.cars.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractRepo extends JpaRepository<Contract, Long> {
    List<Contract> getContractsByUser(User user);
}

package com.sapiofan.cars.repos;

import com.sapiofan.cars.entities.Car;
import com.sapiofan.cars.entities.Contract;
import com.sapiofan.cars.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ContractRepo extends JpaRepository<Contract, Long> {
    List<Contract> getContractsByUser(User user);

    List<Contract> getContractsByCar(Car car);

    @Query("select c from Contract c where c.start_rent >= :start and c.start_rent <= :end")
    List<Contract> getTimeHistoryStart(Date start, Date end);

    @Query("select c from Contract c where c.end_rent >= :start and c.end_rent <= :end")
    List<Contract> getTimeHistoryEnd(Date start, Date end);
}

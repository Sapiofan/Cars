package com.sapiofan.cars.services;

import com.sapiofan.cars.entities.Car;
import com.sapiofan.cars.entities.CarPreferences;
import com.sapiofan.cars.entities.Contract;
import com.sapiofan.cars.entities.User;
import com.sapiofan.cars.repos.ContractRepo;
import com.sapiofan.cars.repos.PreferencesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl {

    @Autowired
    private ContractRepo contractRepo;

    @Autowired
    private PreferencesRepo preferencesRepo;

    public List<Contract> getContractsOfUser(User user) {
        return contractRepo.getContractsByUser(user);
    }

    public Contract createContract(Date start, Date end, Set<String> preferences,
                                   Car car, Double end_price, User user) {
        if (start == null || end == null || car == null || (end_price == null || end_price < 0) || user == null) {
            return null;
        }

        Set<CarPreferences> carPreferences = new HashSet<>();
        if (preferences != null && preferences.size() > 0) {
            carPreferences = preferences.stream()
                    .map(preference -> preferencesRepo.getCarPreferencesByPreference(preference))
                    .collect(Collectors.toSet());
        }

        return contractRepo.save(new Contract(start, end, end_price, user, car, carPreferences));
    }

    public CarPreferences addPreference(CarPreferences carPreferences) {
        return preferencesRepo.save(carPreferences);
    }
}

package com.sapiofan.cars.repos;

import com.sapiofan.cars.entities.CarPreferences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferencesRepo extends JpaRepository<CarPreferences, Long> {
    CarPreferences getCarPreferencesByPreference(String name);
}

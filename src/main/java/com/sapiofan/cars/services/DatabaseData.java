package com.sapiofan.cars.services;

import com.sapiofan.cars.entities.Car;
import com.sapiofan.cars.entities.CarPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class DatabaseData {

    @Autowired
    private CarsServiceImpl carsService;

    @Autowired
    private ContractServiceImpl contractService;

    @Bean
    public void fillDatabase() {
        carsService.add(new Car("7 Series", "BMV", "sedan", 2023,
                25000, 350, 9400, "automatic", (byte) 4, 3.9, "oil",
                15000, "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/09/2023_Mercedes-Benz_Sclass_Gallery1.jpg"));
        carsService.add(new Car("S-Class", "Mercedes-Benz", "sedan", 2023, 40000,
                390, 9900, "automatic", (byte) 4, 3.2, "gas",
                15000, "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/07/2023_BMW_7_Series_Gallery1.jpg"));
        carsService.add(new Car("G-90", "Genesis", "sedan", 2023, 30000, 370,
                8900, "manual", (byte) 4, 3.9, "oil",
                15000, "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/08/2023_Genesis_G90_Gallery1.jpg"));
        carsService.add(new Car("S-90", "Volvo", "sedan", 2022, 20000, 310,
                8500, "automatic", (byte) 4, 3.5, "oil",
                15000, "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/01/2022_Volvo_S90_Gallery1.jpg"));
        carsService.add(new Car("Panamera", "Porsche", "sedan", 2022, 45000, 380,
                9700, "automatic", (byte) 4, 4.2, "oil", 15000,
                "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/01/2022_Porsche_Panamera_Gallery1.jpg"));

        contractService.addPreference(new CarPreferences("Страхування"));
        contractService.addPreference(new CarPreferences("Дитяче крісло"));
        contractService.addPreference(new CarPreferences("wi-fi роутер"));
        contractService.addPreference(new CarPreferences("Холодильник"));
        contractService.addPreference(new CarPreferences("GPS-навігатор"));
        contractService.addPreference(new CarPreferences("Напої/закуски"));
    }
}

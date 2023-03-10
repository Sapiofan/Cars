package com.sapiofan.cars.services;

import com.sapiofan.cars.entities.Car;
import com.sapiofan.cars.entities.CarPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseData {

    @Autowired
    private CarsServiceImpl carsService;

    @Autowired
    private ContractServiceImpl contractService;

    @Bean
    public void fillDatabase() {
        if (carsService.getAllCars().size() == 0) {
            carsService.add(new Car("7 Series", "BMV", "седан", 2023,
                    25000, 350, 9400, "автомат", (byte) 4, 3.9, "бензин",
                    15000, "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/09/2023_Mercedes-Benz_Sclass_Gallery1.jpg"));
            carsService.add(new Car("S-Class", "Mercedes-Benz", "седан", 2023, 4000,
                    390, 2900, "автомат", (byte) 4, 3.2, "бензин",
                    15000, "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/07/2023_BMW_7_Series_Gallery1.jpg"));
            carsService.add(new Car("G-90", "Genesis", "седан", 2023, 3000, 370,
                    2300, "механіка", (byte) 4, 3.9, "бензин",
                    15000, "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/08/2023_Genesis_G90_Gallery1.jpg"));
            carsService.add(new Car("S-90", "Volvo", "седан", 2022, 4200, 310,
                    3100, "автомат", (byte) 4, 3.5, "бензин",
                    15000, "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/01/2022_Volvo_S90_Gallery1.jpg"));
            carsService.add(new Car("A8", "Audi", "седан", 2022, 3800, 330,
                    3600, "механіка", (byte) 4, 3.4, "бензин", 15000,
                    "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/03/2022_Audi_A8_Gallery1.jpg"));
            carsService.add(new Car("A3", "Audi", "седан", 2022, 3200, 320,
                    3100, "автомат", (byte) 4, 2.6, "електро", 15000,
                    "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/01/2022_Audi_A3_Gallery1.jpg"));
            carsService.add(new Car("Panamera", "Porsche", "седан", 2022, 4500, 380,
                    2500, "автомат", (byte) 4, 4.2, "бензин", 15000,
                    "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/01/2022_Porsche_Panamera_Gallery1.jpg"));
            carsService.add(new Car("Integra", "Acura", "седан", 2021, 2700, 290,
                    3700, "автомат", (byte) 4, 2.4, "бензин", 15000,
                    "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/08/2023_Acura_Integra_Gallery1.jpg"));
            carsService.add(new Car("A4", "Audi", "седан", 2022, 3600, 360,
                    2700, "механіка", (byte) 4, 4.6, "бензин", 15000,
                    "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/01/2022_Audi_A4_Gallery1.jpg"));
            carsService.add(new Car("i4", "BMW", "седан", 2022, 4800, 370,
                    2100, "механіка", (byte) 4, 3.9, "бензин", 15000,
                    "https://thumbor.forbes.com/thumbor/fit-in/960x600/filters:format(jpg)/https://www.forbes.com/wheels/wp-content/uploads/2022/01/2022BMWi4_Gallery1.png"));
            carsService.add(new Car("S-60", "Volvo", "седан", 2023, 2800, 300,
                    1900, "механіка", (byte) 4, 2.7, "бензин", 15000,
                    "https://thumbor.forbes.com/thumbor/fit-in/960x600/filters:format(jpg)/https://www.forbes.com/wheels/wp-content/uploads/2022/11/2023VolvoS60_Gallery1.png"));
            carsService.add(new Car("4 Series", "BMW", "кабріолет", 2022, 5000, 320,
                    3000, "автомат", (byte) 4, 3.7, "бензин", 15000,
                    "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/02/2022_BMW_4_Series_Gallery12.jpg"));
            carsService.add(new Car("G70", "Genesis", "седан", 2023, 4000, 380,
                    3100, "автомат", (byte) 4, 4.3, "бензин", 15000,
                    "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/07/2023_Genesis_G70_g1.jpg"));
            carsService.add(new Car("3 series", "BMW", "седан", 2023, 6000, 390,
                    2800, "автомат", (byte) 4, 4.9, "бензин", 15000,
                    "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/01/2022_BMW_3_Series_Gallery1.jpg"));
            carsService.add(new Car("Giulia", "Alpha Romeo", "седан", 2023, 3900, 350,
                    3200, "механіка", (byte) 4, 3.8, "бензин", 15000,
                    "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/08/2023_Alfa_Romeo_Giulia_g1.jpg"));
            carsService.add(new Car("CT4", "Cadillac", "седан", 2023, 5200, 340,
                    2400, "автомат", (byte) 4, 3.2, "бензин", 15000,
                    "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/05/2023_Cadillac_CT4_Gallery1B.jpg"));
            carsService.add(new Car("CLA", "Mercedes-Benz", "седан", 2022, 5300, 330,
                    3600, "автомат", (byte) 4, 3.6, "бензин", 15000,
                    "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2022/07/2022_MercedesBenz_CLA_Gallery1.jpg"));
            carsService.add(new Car("2 Series", "BMV", "седан", 2022, 4400, 310,
                    3500, "механіка", (byte) 4, 3.1, "бензин", 15000,
                    "https://thumbor.forbes.com/thumbor/fit-in/960x600/filters:format(jpg)/https://www.forbes.com/wheels/wp-content/uploads/2022/01/2022_BMW_2Series_Gallery1.png"));

            contractService.addPreference(new CarPreferences("Страхування"));
            contractService.addPreference(new CarPreferences("Дитяче крісло"));
            contractService.addPreference(new CarPreferences("wi-fi роутер"));
            contractService.addPreference(new CarPreferences("Холодильник"));
            contractService.addPreference(new CarPreferences("GPS-навігатор"));
            contractService.addPreference(new CarPreferences("Напої/закуски"));
        }
    }
}

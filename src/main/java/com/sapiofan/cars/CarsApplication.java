package com.sapiofan.cars;

import com.sapiofan.cars.services.DatabaseData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CarsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CarsApplication.class, args);

        context.getBean(DatabaseData.class).fillDatabase();
    }

}

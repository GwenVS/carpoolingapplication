package be.kdg.ip2.carpoolingapplication;

import be.kdg.ip2.carpoolingapplication.domain.Car;
import be.kdg.ip2.carpoolingapplication.domain.Carpooler;
import be.kdg.ip2.carpoolingapplication.domain.Gender;
import be.kdg.ip2.carpoolingapplication.repositories.CarRepository;
import be.kdg.ip2.carpoolingapplication.repositories.CarpoolerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * This class is used for placing initial data into the database
 */

@Component
public class DataLoader implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private CarpoolerRepository carpoolerRepository;
    @Autowired
    private CarRepository carRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Carpooler newCarpooler1 = new Carpooler("John", "Doe", "john@Doe.com", true, Gender.MALE, new ArrayList<Car>());
        Carpooler savedCarpooler1 = this.carpoolerRepository.save(newCarpooler1);
        Car car1 = new Car("Mercedes", 7.8, 3, savedCarpooler1);
        savedCarpooler1.addCar(car1);
        this.carpoolerRepository.save(savedCarpooler1);
    }
}

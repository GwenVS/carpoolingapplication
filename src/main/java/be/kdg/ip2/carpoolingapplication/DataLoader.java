package be.kdg.ip2.carpoolingapplication;

import be.kdg.ip2.carpoolingapplication.domain.*;
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

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        Carpooler newCarpooler_M_S_1 = new Carpooler("John", "Doe", "john@Doe.com", true, Gender.MALE, new ArrayList<Car>(), new ArrayList<CarpoolerRideInfo>());
        Carpooler savedCarpooler_M_S_1 = this.carpoolerRepository.save(newCarpooler_M_S_1);
        Car car1 = new Car("Mercedes", 7.8, 3, savedCarpooler_M_S_1);
        savedCarpooler_M_S_1.addCar(car1);
        this.carpoolerRepository.save(savedCarpooler_M_S_1);


        Carpooler newCarpooler_F_S_2 = new Carpooler("Jane", "Doe", "jane@Doe.com", true, Gender.FEMALE, new ArrayList<Car>(), new ArrayList<CarpoolerRideInfo>());
        Carpooler savedCarpooler_F_S_2 = this.carpoolerRepository.save(newCarpooler_F_S_2);
        Car car2 = new Car("Opel", 6.8, 5, savedCarpooler_F_S_2);
        savedCarpooler_F_S_2.addCar(car2);
        Car car3 = new Car("Toyota", 6.3, 1, savedCarpooler_F_S_2);
        savedCarpooler_F_S_2.addCar(car3);
        this.carpoolerRepository.save(savedCarpooler_F_S_2);


        Carpooler newCarpooler_M_NS_0 = new Carpooler("John", "Roe", "john@Roe.com", false, Gender.MALE, new ArrayList<Car>(), new ArrayList<CarpoolerRideInfo>());
        this.carpoolerRepository.save(newCarpooler_M_NS_0);

        
        Carpooler newCarpooler_F_NS_1 = new Carpooler("Jane", "Roe", "jane@Roe.com", false, Gender.FEMALE, new ArrayList<Car>(), new ArrayList<CarpoolerRideInfo>());
        Carpooler savedCarpooler_F_NS_1 = this.carpoolerRepository.save(newCarpooler_F_NS_1);
        Car car4 = new Car("Daihatsu", 9.8, 2, savedCarpooler_F_NS_1);
        savedCarpooler_F_NS_1.addCar(car4);
        this.carpoolerRepository.save(savedCarpooler_F_NS_1);
    }
}

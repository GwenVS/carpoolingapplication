package be.kdg.ip2.carpoolingapplication;

import be.kdg.ip2.carpoolingapplication.domain.*;
import be.kdg.ip2.carpoolingapplication.repositories.CarpoolerRepository;
import be.kdg.ip2.carpoolingapplication.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class is used for placing initial data into the database
 */
@Component
public class DataLoader implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private CarpoolerRepository carpoolerRepository;

    @Autowired
    private RideRepository rideRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        // carpoolers
        Carpooler newCarpooler_M_S_1 = new Carpooler("John", "Doe", "savedCarpooler_M_S_1@Doe.com", true, Gender.MALE);
        Carpooler savedCarpooler_M_S_1 = this.carpoolerRepository.save(newCarpooler_M_S_1);
        Car car1 = new Car("Mercedes", 7.8, 3, savedCarpooler_M_S_1);
        savedCarpooler_M_S_1.addCar(car1);
        this.carpoolerRepository.save(savedCarpooler_M_S_1);

        Carpooler newCarpooler_F_S_2 = new Carpooler("Jane", "Doe", "savedCarpooler_F_S_2@Doe.com", true, Gender.FEMALE);
        Carpooler savedCarpooler_F_S_2 = this.carpoolerRepository.save(newCarpooler_F_S_2);
        Car car2 = new Car("Opel", 6.8, 5, savedCarpooler_F_S_2);
        savedCarpooler_F_S_2.addCar(car2);
        Car car3 = new Car("Toyota", 6.3, 1, savedCarpooler_F_S_2);
        savedCarpooler_F_S_2.addCar(car3);
        this.carpoolerRepository.save(savedCarpooler_F_S_2);

        Carpooler newCarpooler_M_S_0 = new Carpooler("Richard", "Roe", "newCarpooler_M_S_0@Roe.com", true, Gender.MALE);
        Carpooler savedCarpooler_M_S_0 = this.carpoolerRepository.save(newCarpooler_M_S_0);

        Carpooler newCarpooler_M_NS_0 = new Carpooler("John", "Roe", "newCarpooler_M_NS_0@Roe.com", false, Gender.MALE);
        this.carpoolerRepository.save(newCarpooler_M_NS_0);

        Carpooler newCarpooler_F_NS_1 = new Carpooler("Jane", "Roe", "savedCarpooler_F_NS_1@Roe.com", false, Gender.FEMALE);
        Carpooler savedCarpooler_F_NS_1 = this.carpoolerRepository.save(newCarpooler_F_NS_1);
        savedCarpooler_F_NS_1.addCar(new Car("Daihatsu", 9.8, 2, savedCarpooler_F_NS_1));
        this.carpoolerRepository.save(savedCarpooler_F_NS_1);


        // testride with 3 seats
        Ride ride1 = new Ride(LocalDateTime.of(2019, 1, 1, 7, 30), LocalDateTime.of(2019, 1, 1, 17, 30), 2, new ArrayList<Location>(), new ArrayList<CarpoolerRideInfo>(), new ArrayList<RideRequest>());
        Ride savedRide1 = this.rideRepository.save(ride1);
        // passagepoints
        savedRide1.addPassagePoint(new Location(51.260197, 4.402771, savedRide1));//antwerpen
        savedRide1.addPassagePoint(new Location(51.02574, 4.47762, savedRide1));//mechelen
        savedRide1.addPassagePoint(new Location(50.85045, 4.34878, savedRide1));//brussel
        // rideinfo for driver + 1 passenger
        CarpoolerRideInfo carpoolerRideInfo1 = new CarpoolerRideInfo(true, savedCarpooler_M_S_1, savedRide1);
        savedRide1.addCarpoolerRideInfo(carpoolerRideInfo1);
        savedCarpooler_M_S_1.addCarpoolerRideInfo(carpoolerRideInfo1);
        this.carpoolerRepository.save(savedCarpooler_M_S_1);
        this.rideRepository.save(savedRide1);
        CarpoolerRideInfo carpoolerRideInfo2 = new CarpoolerRideInfo(false, savedCarpooler_M_S_0, savedRide1);
        savedRide1.addCarpoolerRideInfo(carpoolerRideInfo2);
        savedCarpooler_M_S_0.addCarpoolerRideInfo(carpoolerRideInfo2);
        this.carpoolerRepository.save(savedCarpooler_M_S_0);
        this.rideRepository.save(savedRide1);
        // 1 riderequest
        RideRequest rideRequest1 = new RideRequest(savedCarpooler_F_S_2, savedRide1, "I would like to join you for this ride");
        savedRide1.addRideRequest(rideRequest1);
        savedCarpooler_F_S_2.addRideRequest(rideRequest1);
        this.carpoolerRepository.save(savedCarpooler_F_S_2);
        this.rideRepository.save(savedRide1);
    }
}

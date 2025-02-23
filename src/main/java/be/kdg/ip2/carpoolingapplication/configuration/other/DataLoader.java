package be.kdg.ip2.carpoolingapplication.configuration.other;

import be.kdg.ip2.carpoolingapplication.domain.*;
import be.kdg.ip2.carpoolingapplication.domain.locations.EndLocation;
import be.kdg.ip2.carpoolingapplication.domain.locations.RideLocation;
import be.kdg.ip2.carpoolingapplication.domain.locations.StartLocation;
import be.kdg.ip2.carpoolingapplication.domain.user.UserRideInfo;
import be.kdg.ip2.carpoolingapplication.services.declaration.ICarService;
import be.kdg.ip2.carpoolingapplication.services.declaration.IRideService;
import be.kdg.ip2.carpoolingapplication.services.declaration.IUserService;
import be.kdg.ip2.carpoolingapplication.services.exceptions.RideServiceException;
import be.kdg.ip2.carpoolingapplication.services.implementation.CustomUserDetailsService;
import be.kdg.ip2.carpoolingapplication.domain.enums.Gender;
import be.kdg.ip2.carpoolingapplication.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for placing initial tryout data into the database
 * *******ONLY FOR TESTING********
 * Needs spring.jpa.hibernate.ddl-auto=create (@application.properties)
 */

@Component
public class DataLoader implements ApplicationListener<ApplicationReadyEvent> {
/*
    private CustomUserDetailsService customUserDetailsService;
    private IUserService userService;
    private IRideService rideService;
    private ICarService carService;

    @Autowired
    public DataLoader(CustomUserDetailsService customUserDetailsService, IUserService userService, IRideService rideService, ICarService carService) {
        this.customUserDetailsService = customUserDetailsService;
        this.userService = userService;
        this.rideService = rideService;
        this.carService = carService;
    }
*/
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
/*
        User carpooler_M_S_1 =new User("John", "Doe", "john", "carpooler_M_S_1@Doe.com", LocalDate.of(1997,3,5), "testtest", Gender.Male, new ArrayList<>());
        this.customUserDetailsService.addUser(carpooler_M_S_1);
        carpooler_M_S_1.setIsSmoker(true);
        Car car1 = new Car("Daihatsu terios", 8.5, 3, carpooler_M_S_1);
        carpooler_M_S_1.addCar(car1);
        carpooler_M_S_1.addCar(new Car("citroën DS4", 6.2, 2, carpooler_M_S_1));
        User savedUser = userService.saveUser(carpooler_M_S_1);
        User carpooler_F_S_2 = this.customUserDetailsService.addUser(new User("Jane", "Doe", "jane", "carpooler_F_S_2@Doe.com", LocalDate.of(1996, 2, 1), "testtest", Gender.Female, new ArrayList<>()));
        carpooler_F_S_2.setIsSmoker(true);
        userService.saveUser(carpooler_F_S_2);
        User carpooler_M_NS_1 = this.customUserDetailsService.addUser(new User("Richard", "Roe", "richard", "carpooler_M_NS_1@Roe.com", LocalDate.of(1991, 11, 1), "testtest", Gender.Male, new ArrayList<>()));


        // testride with 3 seats
        Ride ride1 = null;
        try {
            ride1 = this.rideService.saveRide(new Ride(LocalDateTime.of(2019, 1, 1, 7, 30), LocalDateTime.of(2019, 1, 1, 17, 30), savedUser.getCars().get(0)));
        } catch (RideServiceException e) {
            System.out.println("@DataLoader: if this goes wrong, i dunno anymore xD");
        }

        // passagepoints
        List<RideLocation> locations = new ArrayList<RideLocation>();
        locations.add(new RideLocation(51.260197, 4.402771, ride1));//antwerpen
        locations.add(new RideLocation(51.02574, 4.47762, ride1));//mechelen
        locations.add(new RideLocation(50.85045, 4.34878, ride1));//brussel
        ride1.setLocations(locations);
        // rideinfo 1 passenger
        UserRideInfo userRideInfo2 = new UserRideInfo(false, carpooler_F_S_2, ride1);
        ride1.addUserRideInfo(userRideInfo2);
        carpooler_F_S_2.addUserRideInfo(userRideInfo2);
        // 1 riderequest
        //todo: edit constructor for dummy upload StartLocation - EndLocation
        RideRequest rideRequest1 = new RideRequest(carpooler_M_NS_1, ride1, "I would like to join you for this ride");
        ride1.addRideRequest(rideRequest1);
        carpooler_M_NS_1.addRideRequest(rideRequest1);
        try {
            this.rideService.createRide(carpooler_M_S_1.getUsername(),ride1);
        } catch (RideServiceException e) {
            System.out.println("@DataLoader: if this goes wrong, i dunno anymore xD");
        }
*/
    }
}

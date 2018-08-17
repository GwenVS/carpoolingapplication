package be.kdg.ip2.carpoolingapplication.configuration.other;

import be.kdg.ip2.carpoolingapplication.domain.Location;
import be.kdg.ip2.carpoolingapplication.domain.Ride;
import be.kdg.ip2.carpoolingapplication.domain.RideRequest;
import be.kdg.ip2.carpoolingapplication.domain.user.UserRideInfo;
import be.kdg.ip2.carpoolingapplication.services.RideService;
import be.kdg.ip2.carpoolingapplication.services.exceptions.RideServiceException;
import be.kdg.ip2.carpoolingapplication.services.implementation.CustomUserDetailsService;
import be.kdg.ip2.carpoolingapplication.domain.user.Gender;
import be.kdg.ip2.carpoolingapplication.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class is used for placing initial data into the database
 */

@Component
public class DataLoader implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private CustomUserDetailsService userService;

    @Autowired
    private RideService rideService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        User carpooler_M_S_1 = this.userService.addUser(new User("John", "Doe", "john", "carpooler_M_S_1@Doe.com", LocalDate.of(1997,3,5), "testtest", Gender.Male, new ArrayList<>()));

        User carpooler_F_S_2 = this.userService.addUser(new User("Jane", "Doe", "jane", "carpooler_F_S_2@Doe.com", LocalDate.of(1996, 2, 1), "testtest", Gender.Female, new ArrayList<>()));

        User carpooler_M_NS_1 = this.userService.addUser(new User("Richard", "Roe", "richard", "carpooler_M_NS_1@Roe.com", LocalDate.of(1991, 11, 1), "testtest", Gender.Male, new ArrayList<>()));


        // testride with 3 seats
        Ride ride1 = null;
        try {
            ride1 = this.rideService.saveRide(new Ride(LocalDateTime.of(2019, 1, 1, 7, 30), LocalDateTime.of(2019, 1, 1, 17, 30)));
        } catch (RideServiceException e) {
            System.out.println("@DataLoader: if this goes wrong, i dunno anymore xD");
        }
        // passagepoints
        ride1.addLocation(new Location(51.260197, 4.402771, ride1));//antwerpen
        ride1.addLocation(new Location(51.02574, 4.47762, ride1));//mechelen
        ride1.addLocation(new Location(50.85045, 4.34878, ride1));//brussel
        // rideinfo for driver + 1 passenger
        UserRideInfo userRideInfo1 = new UserRideInfo(true, carpooler_M_S_1, ride1);
        ride1.addUserRideInfo(userRideInfo1);
        carpooler_M_S_1.addUserRideInfo(userRideInfo1);
        UserRideInfo userRideInfo2 = new UserRideInfo(false, carpooler_F_S_2, ride1);
        ride1.addUserRideInfo(userRideInfo2);
        carpooler_F_S_2.addUserRideInfo(userRideInfo2);
        // 1 riderequest
        RideRequest rideRequest1 = new RideRequest(carpooler_M_NS_1, ride1, "I would like to join you for this ride");
        ride1.addRideRequest(rideRequest1);
        carpooler_M_NS_1.addRideRequest(rideRequest1);
        try {
            this.rideService.saveRide(ride1);
        } catch (RideServiceException e) {
            System.out.println("@DataLoader: if this goes wrong, i dunno anymore xD");
        }

    }
}

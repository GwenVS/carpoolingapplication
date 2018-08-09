package be.kdg.ip2.backend.configuration.other;

import be.kdg.ip2.backend.domain.Location;
import be.kdg.ip2.backend.domain.Ride;
import be.kdg.ip2.backend.domain.RideRequest;
import be.kdg.ip2.backend.domain.user.UserRideInfo;
import be.kdg.ip2.backend.services.RideService;
import be.kdg.ip2.backend.services.implementation.CustomUserDetailsService;
import be.kdg.ip2.backend.domain.user.Gender;
import be.kdg.ip2.backend.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

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

        User carpooler_M_S_1 = this.userService.addUser(new User("John", "Doe", "john", "carpooler_M_S_1@Doe.com", 6, 3, 1997, "testtest", Gender.Male, new ArrayList<>()));

        User carpooler_F_S_2 = this.userService.addUser(new User("Jane", "Doe", "jane", "carpooler_F_S_2@Doe.com", 1, 2, 1996, "testtest", Gender.Female, new ArrayList<>()));

        User carpooler_M_NS_1 = this.userService.addUser(new User("Richard", "Roe", "richard", "carpooler_M_NS_1@Roe.com", 1, 4, 1984, "testtest", Gender.Male, new ArrayList<>()));


        // testride with 3 seats
        Ride ride1 = this.rideService.saveRide(new Ride(LocalDateTime.of(2019, 1, 1, 7, 30), LocalDateTime.of(2019, 1, 1, 17, 30)));
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
        this.rideService.saveRide(ride1);

    }
}

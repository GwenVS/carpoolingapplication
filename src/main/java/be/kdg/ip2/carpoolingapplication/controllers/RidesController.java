package be.kdg.ip2.carpoolingapplication.controllers;
import be.kdg.ip2.carpoolingapplication.domain.Ride;
import be.kdg.ip2.carpoolingapplication.services.RideService;
import be.kdg.ip2.carpoolingapplication.services.exceptions.RideServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://carpoolingapplicationfe.herokuapp.com")
public class RidesController {
    private static final Logger logger = LogManager.getLogger(RidesController.class);

    @Autowired
    private RideService rideService;

    @GetMapping("/api/public/rides")
    public List<Ride> getAllRides() {
        logger.info("@RidesController: searching all rides.");
        return rideService.getAllRides();
    }

    //todo: return responsestatus
    @PostMapping(("/api/private/rides"))
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity createRide(@RequestBody Ride ride) {
        try {
            Ride ride1 = rideService.saveRide(ride);
            logger.info("@RidesController: new ride created.");
            return ResponseEntity.ok(ride1);
        } catch (RideServiceException e) {
            logger.error("@RidesController: error while creating new ride: " + ride.toString() + "error: "+  e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("RideServiceException: "+ e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong while registering. Try again later");
        }
    }

    //todo: return responsestatus
    @GetMapping("/api/public/rides/{ride_id}")
    public ResponseEntity get(@PathVariable("ride_id") long rideId) {
        try {
            Ride ride = rideService.getRide(rideId);
            return ResponseEntity.ok(ride);
        } catch (RideServiceException e) {
            logger.error("@RidesController: error while getting ride: rideId: " + rideId, e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("RideServiceException: "+ e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong while registering. Try again later");
        }
    }

    @GetMapping("/api/public/rides/time/{min_departure_time}/{max_departure_time}")
    public List<Ride> getRidesByDepartureTime(@PathVariable("min_departure_time")LocalDateTime minDepartureTime, @PathVariable("max_departure_time")LocalDateTime maxDepartureTime){
        logger.info("searching rides departing from " + minDepartureTime + " till " + maxDepartureTime +".");
        return rideService.getRidesByDepartureTime(minDepartureTime, maxDepartureTime);
    }
}

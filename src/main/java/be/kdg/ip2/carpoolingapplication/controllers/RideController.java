package be.kdg.ip2.carpoolingapplication.controllers;

import be.kdg.ip2.carpoolingapplication.domain.Ride;
import be.kdg.ip2.carpoolingapplication.services.declaration.IRideService;
import be.kdg.ip2.carpoolingapplication.services.exceptions.RideServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://carpoolingapplicationfe.herokuapp.com")
public class RideController {
    private static final Logger logger = LogManager.getLogger(RideController.class);

    private IRideService rideService;

    @Autowired
    public RideController(IRideService rideService) {
        this.rideService = rideService;
    }

    @GetMapping("/api/public/rides")
    public List<Ride> getAllRides() {
        logger.info("@RidesController: searching all rides.");
        return rideService.getAllRides();
    }

    //create new ride
    @PostMapping(("/api/public/rides/{username}"))
    //@PostMapping(("/api/private/rides/{user_id}"))
    //@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity createRide(@PathVariable("username") String username, @RequestBody Ride ride) {
        try {
            Ride createdRide = rideService.createRide(username, ride);
            logger.info("@RidesController: new ride created.");
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRide);
        } catch (RideServiceException e) {
            logger.error("@RidesController: error while creating new ride: " + ride.toString() + "error: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("RideServiceException: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong while creating your ride. Try again later");
        }
    }

    @GetMapping("/api/public/rides/{ride_id}")
    public ResponseEntity get(@PathVariable("ride_id") long rideId) {
        try {
            Ride ride = rideService.getRideById(rideId);
            return ResponseEntity.ok(ride);
        } catch (RideServiceException e) {
            logger.error("@RidesController: error while getting ride: rideId: " + rideId, e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("RideServiceException: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong while fetching ride " + rideId + ". Try again later");
        }
    }

    @GetMapping("/api/public/rides/time/{min_departure_time}/{max_departure_time}")
    public List<Ride> getRidesByDepartureTime(@PathVariable("min_departure_time") LocalDateTime minDepartureTime, @PathVariable("max_departure_time") LocalDateTime maxDepartureTime) {
        logger.info("searching rides departing from " + minDepartureTime + " till " + maxDepartureTime + ".");
        return rideService.getRidesByDepartureTime(minDepartureTime, maxDepartureTime);
    }
}

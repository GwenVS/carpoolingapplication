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
    private static final String RIDE_URL = "/api/public/rides";
    private static final String PUBLIC_RIDE_URL = "/api/public/rides";

    private IRideService rideService;

    @Autowired
    public RideController(IRideService rideService) {
        this.rideService = rideService;
    }

    /**
     * find ride by rideId
     * @param rideId
     * @return Ride
     */
    @GetMapping(RIDE_URL + "/{ride_id}")
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

    /**
     * find all rides for 1 user with given username
     * @param username
     * @return List<Ride>
     */
    @GetMapping(RIDE_URL + "/user/{username}")
    public ResponseEntity getRidesByUser(@PathVariable String username) {
        List<Ride> rides = rideService.getRidesByUsername(username);
        logger.info("@RidesController: fetched Rides for user " + username);
        return ResponseEntity.status(HttpStatus.OK).body(rides);
    }

    /**
     * find all rides between min and max departureTime
     * @param minDepartureTime
     * @param maxDepartureTime
     * @return
     */
    @GetMapping(RIDE_URL + "/time/{min_departure_time}/{max_departure_time}")
    public List<Ride> getRidesByDepartureTime(@PathVariable("min_departure_time") LocalDateTime minDepartureTime, @PathVariable("max_departure_time") LocalDateTime maxDepartureTime) {
        logger.info("searching rides departing from " + minDepartureTime + " till " + maxDepartureTime + ".");
        return rideService.getRidesByDepartureTime(minDepartureTime, maxDepartureTime);
    }


    /***
     * fetches first 10 rides
     * @return List<Ride>
     */
    @GetMapping(RIDE_URL)
    public List<Ride> getRides() {
        logger.info("@RidesController: searching departing rides.");
        return rideService.getDepartingRides();
    }

    /**
     * creates a new ride
     * @param username
     * @param ride
     * @return Ride
     */
    @PostMapping(RIDE_URL + "/ride/{username}")
    //@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity createRide(@PathVariable String username, @RequestBody Ride ride) {
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

    /**
     * delete a ride with given rideId
     * @param ride_id
     * @return
     */
    @DeleteMapping(RIDE_URL + "/{ride_id}")
    public ResponseEntity deleteRide(@PathVariable Long ride_id) {
        try {
            rideService.deleteRide(ride_id);
            return ResponseEntity.ok().build();
        } catch (RideServiceException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong while deleting your ride. Try again later");
        }
    }
}

package be.kdg.ip2.carpoolingapplication.controllers;
import be.kdg.ip2.carpoolingapplication.domain.Ride;
import be.kdg.ip2.carpoolingapplication.services.RideService;
import be.kdg.ip2.carpoolingapplication.services.exceptions.RideServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://carpoolingapplication.herokuapp.com")
public class RidesController {
    private static final Logger logger = LogManager.getLogger(RidesController.class);

    @Autowired
    private RideService rideService;

    @GetMapping("/api/public/rides")
    public List<Ride> getAllRides() {
        logger.info("@RidesController: searching all rides.");
        return rideService.getAllRides();
    }

    @PostMapping(("/api/private/rides"))
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER')")
    public Ride createRide(@RequestBody Ride ride) throws RideServiceException {
        try {
            Ride ride1 = rideService.saveRide(ride);
            logger.info("@RidesController: new ride created.");
            return ride1;
        } catch (RideServiceException e) {
            logger.error("@RidesController: error while creating new ride: " + ride.toString() + "error: "+  e.getMessage(), e);
            throw new RideServiceException(e.getMessage());
        }
    }

    @GetMapping("/api/public/rides/{ride_id}")
    public Ride get(@PathVariable("ride_id") long rideId) throws RideServiceException {

        try {
            return rideService.getRide(rideId);
        } catch (RideServiceException e) {
            logger.error("@RidesController: error while getting ride: rideId: " + rideId, e);
            throw new RideServiceException(e.getMessage());
        }
    }

    @GetMapping("/api/public/rides/time/{min_departure_time}/{max_departure_time}")
    public List<Ride> getRidesByDepartureTime(@PathVariable("min_departure_time")LocalDateTime minDepartureTime, @PathVariable("max_departure_time")LocalDateTime maxDepartureTime){
        logger.info("searching rides departing from " + minDepartureTime + " till " + maxDepartureTime +".");
        return rideService.getRidesByDepartureTime(minDepartureTime, maxDepartureTime);
    }
}

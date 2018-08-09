package be.kdg.ip2.backend.controllers;
import be.kdg.ip2.backend.domain.Ride;
import be.kdg.ip2.backend.services.RideService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:9090")
public class RidesController {
    private final Logger logger = Logger.getLogger(RidesController.class);

    @Autowired
    private RideService rideService;

    @GetMapping("/api/public/rides")
    public List<Ride> getAllRides() {
        logger.info("searching all rides.");
        return rideService.getAllRides();
    }

    @PostMapping(("/api/private/rides"))
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER')")
    public void createRide(@RequestBody Ride ride){
        rideService.saveRide(ride);
    }

    @GetMapping("/api/public/rides/{ride_id}")
    public Ride get(@PathVariable("ride_id") long rideId){
        return rideService.getRide(rideId);
    }

    @GetMapping("/api/public/rides/time/{min_departure_time}/{max_departure_time}")
    public List<Ride> getRidesByDepartureTime(@PathVariable("min_departure_time")LocalDateTime minDepartureTime, @PathVariable("max_departure_time")LocalDateTime maxDepartureTime){
        logger.info("searching rides departing from " + minDepartureTime + " till " + maxDepartureTime +".");
        return rideService.getRidesByDepartureTime(minDepartureTime, maxDepartureTime);
    }
}

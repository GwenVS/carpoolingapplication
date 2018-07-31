package be.kdg.ip2.carpoolingapplication.controllers;


import be.kdg.ip2.carpoolingapplication.domain.Ride;
import be.kdg.ip2.carpoolingapplication.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
public class RidesController {


    @Autowired
    private RideService rideService;

    @GetMapping
    public List<Ride> findAllRides() {
        return rideService.findAllRides();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createRide(@RequestBody Ride ride){
        rideService.createRide(ride);
    }

    @GetMapping("/{ride_id}")
    public Ride get(@PathVariable("ride_id") long rideId){
        return rideService.getRide(rideId);
    }
}

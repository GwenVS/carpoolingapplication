package be.kdg.ip2.carpoolingapplication.controllers;


import be.kdg.ip2.carpoolingapplication.domain.Ride;
import be.kdg.ip2.carpoolingapplication.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
public class RidesController {


    @Autowired
    private RideRepository rideRepository;

    @GetMapping
    public List<Ride> findAllRides() {
        return rideRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createRide(@RequestBody Ride ride){
        rideRepository.save(ride);
    }

    @GetMapping("/{ride_id}")
    public Ride get(@PathVariable("ride_id") long rideId){
        return rideRepository.getOne(rideId);
    }
}

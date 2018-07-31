package be.kdg.ip2.carpoolingapplication.services;


import be.kdg.ip2.carpoolingapplication.domain.Ride;
import be.kdg.ip2.carpoolingapplication.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Primary
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public RideService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    public List<Ride> findAllRides() {
        List<Ride> rides = rideRepository.findAll();
        if (rides == null) {
            return new ArrayList<>();
        } else {
            return rides;
        }
    }


    public void createRide(Ride ride) {
        Ride r = rideRepository.save(ride);
        if (r == null) {
            throw new RideServiceException("Ride not saved");
        }
    }


    public Ride getRide(long rideId) {
        Ride ride = rideRepository.getOne(rideId);
        if(ride == null) {
            throw new RideServiceException("Ride not found");
        }
        return ride;
    }

}

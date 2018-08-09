package be.kdg.ip2.backend.services;

import be.kdg.ip2.backend.domain.Ride;
import be.kdg.ip2.backend.repository.RideRepository;
import be.kdg.ip2.backend.services.exceptions.RideServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Primary
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public List<Ride> getAllRides() {
        List<Ride> rides = rideRepository.findAll();
        if (rides == null) {
            return new ArrayList<>();
        } else {
            return rides;
        }
    }

    public Ride saveRide(Ride ride) {
        Ride r = rideRepository.save(ride);
        if (r == null) {
            throw new RideServiceException("Ride not saved");
        }
        return r;
    }


    public Ride getRide(long rideId) {
        Ride ride = rideRepository.getOne(rideId);
        if(ride == null) {
            throw new RideServiceException("Ride not found");
        }
        return ride;
    }

    public List<Ride> getRidesByDepartureTime(LocalDateTime minDepartureTime, LocalDateTime maxDepartureTime) {
        List<Ride> rides = rideRepository.findRidesByDepartureTimeOutwardJourneyBetween(minDepartureTime, maxDepartureTime);
        rides.addAll(rideRepository.findRidesByDepartureTimeReturnTripBetween(minDepartureTime, maxDepartureTime));
        if (rides == null) {
            return new ArrayList<>();
        } else {
            return rides;
        }
    }
}

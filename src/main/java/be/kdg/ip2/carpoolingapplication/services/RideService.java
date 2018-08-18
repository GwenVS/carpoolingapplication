package be.kdg.ip2.carpoolingapplication.services;

import be.kdg.ip2.carpoolingapplication.domain.Car;
import be.kdg.ip2.carpoolingapplication.domain.Location;
import be.kdg.ip2.carpoolingapplication.domain.Ride;
import be.kdg.ip2.carpoolingapplication.domain.SubRide;
import be.kdg.ip2.carpoolingapplication.domain.user.User;
import be.kdg.ip2.carpoolingapplication.domain.user.UserRideInfo;
import be.kdg.ip2.carpoolingapplication.repository.CarRepository;
import be.kdg.ip2.carpoolingapplication.repository.RideRepository;
import be.kdg.ip2.carpoolingapplication.services.declaration.IRideService;
import be.kdg.ip2.carpoolingapplication.services.declaration.UserService;
import be.kdg.ip2.carpoolingapplication.services.exceptions.RideServiceException;
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
public class RideService implements IRideService {

    private UserService userService;
    private RideRepository rideRepository;
    private CarRepository carRepository;

    @Autowired
    public RideService(UserService userService, RideRepository rideRepository, CarRepository carRepository) {
        this.userService = userService;
        this.rideRepository = rideRepository;
        this.carRepository = carRepository;
    }

    @Override
    public List<Ride> getAllRides() {
        List<Ride> rides = rideRepository.findAll();
        if (rides == null) {
            return new ArrayList<>();
        } else {
            return rides;
        }
    }

    //create new ride attached to a user
    @Override
    public Ride createRide(Long userId, Ride ride) throws RideServiceException {
        try {
            User creator = userService.findUserById(userId);
            ride.setCreatorDriverUsername(creator.getUsername());
            List<SubRide> subrides = createSubRides(ride.getLocations());
            ride.setSubRides(subrides);
            ride.addUserRideInfo(creatorUserRideInfo(creator , ride));
            Ride r = rideRepository.save(ride);
            return r;
        } catch (Exception e) {
            throw new RideServiceException("Ride not saved");
        }
    }

    @Override
    public Ride getRide(long rideId) throws RideServiceException {
        Ride ride = rideRepository.getOne(rideId);
        if (ride == null) {
            throw new RideServiceException("Ride not found");
        }
        return ride;
    }

    @Override
    public List<Ride> getRidesByDepartureTime(LocalDateTime minDepartureTime, LocalDateTime maxDepartureTime) {
        List<Ride> rides = rideRepository.findRidesByDepartureTimeOutwardJourneyBetween(minDepartureTime, maxDepartureTime);
        rides.addAll(rideRepository.findRidesByDepartureTimeReturnTripBetween(minDepartureTime, maxDepartureTime));
        if (rides == null) {
            return new ArrayList<>();
        } else {
            return rides;
        }
    }

    @Override
    public Ride createRide(Ride ride) throws RideServiceException {
        Ride r = rideRepository.save(ride);
        if (r == null) {
            throw new RideServiceException("Ride not saved");
        }
        return r;
    }


    private UserRideInfo creatorUserRideInfo(User creator, Ride ride) {
        return new UserRideInfo(true, creator, ride);
    }

    //create a list of subrides with given locations
    private List<SubRide> createSubRides(List<Location> locations) {
        List<SubRide> subRides = new ArrayList<>();
        //loop until 1 before last of locations:
        //a subride contains a begin and endlocation
        // so there is always 1 less subride then locations in the list
        for (int i = 0; i < locations.size()-1; i++) {
            SubRide subRide = new SubRide();
            subRide.setStartLocation(locations.get(i));
            subRide.setStopLocation(locations.get(i+1));
            subRides.add(subRide);
        }
        return subRides;
    }
}

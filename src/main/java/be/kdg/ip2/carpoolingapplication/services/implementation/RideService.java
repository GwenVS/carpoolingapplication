package be.kdg.ip2.carpoolingapplication.services.implementation;

import be.kdg.ip2.carpoolingapplication.domain.*;
import be.kdg.ip2.carpoolingapplication.domain.locations.Location;
import be.kdg.ip2.carpoolingapplication.domain.user.User;
import be.kdg.ip2.carpoolingapplication.domain.user.UserRideInfo;
import be.kdg.ip2.carpoolingapplication.repositories.*;
import be.kdg.ip2.carpoolingapplication.services.declaration.ICarService;
import be.kdg.ip2.carpoolingapplication.services.declaration.IRideService;
import be.kdg.ip2.carpoolingapplication.services.declaration.IUserService;
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

    private IUserService userService;
    private ICarService carService;

    private RideRepository rideRepository;
    private LocationRepository locationRepository;
    private UserRideInfoRepository userRideInfoRepository;
    private RideRequestRepository rideRequestRepository;


    @Autowired
    public RideService(IUserService userService, ICarService carService, RideRepository rideRepository, LocationRepository locationRepository, UserRideInfoRepository userRideInfoRepository, RideRequestRepository rideRequestRepository) {
        this.userService = userService;
        this.rideRepository = rideRepository;
        this.locationRepository = locationRepository;
        this.userRideInfoRepository = userRideInfoRepository;
        this.rideRequestRepository = rideRequestRepository;
        this.carService = carService;
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

    @Override
    public Ride getRideById(long rideId) throws RideServiceException {
        Ride ride = rideRepository.getOne(rideId);
        if (ride == null) {
            throw new RideServiceException("Ride not found");
        }
        return ride;
    }

    @Override
    public List<Ride> getRidesByDepartureTime(LocalDateTime minDepartureTime, LocalDateTime maxDepartureTime){
        List<Ride> rides = rideRepository.findRidesByDepartureTimeOutwardJourneyBetween(minDepartureTime, maxDepartureTime);
        rides.addAll(rideRepository.findRidesByDepartureTimeReturnTripBetween(minDepartureTime, maxDepartureTime));
        if (rides == null) {
            return new ArrayList<>();
        } else {
            return rides;
        }
    }

    //create new ride attached to a user
    @Override
    public Ride createRide(String username, Ride ride) throws RideServiceException {
        try {
            Ride r = saveRide(ride);
            User creator = userService.getUserByUsername(username);
            UserRideInfo uri = creatorUserRideInfo(creator, r);
            r.addUserRideInfo(uri);
            creator.addUserRideInfo(uri);
            saveRide(r);
            saveUser(creator);
            return r;
        } catch (Exception e) {
            throw new RideServiceException("Ride not saved");
        }
    }


    @Override
    public Ride saveRide(Ride ride) throws RideServiceException {
        try {
            return rideRepository.save(ride);
        } catch (Exception e) {
            throw new RideServiceException("Ride not saved");
        }
    }


    @Override
    public List<Ride> getRidesByUsername(String username) {
        List<Ride> rides = new ArrayList<>();
        User user = userService.getUserByUsername(username);
        List<UserRideInfo> userRideInfos = user.getUserRideInfos();
        for (UserRideInfo uri : userRideInfos) {
            rides.add(uri.getRide());
        }
        return rides;
    }

    @Override
    public void deleteRide(Long rideId) throws RideServiceException {
        try {
            Ride ride = rideRepository.findOne(rideId);
            //remove UserRideInfo's from users and delete
            List<UserRideInfo> userRideInfos = ride.getUserRideInfos();
            for (int i =userRideInfos.size()-1; i>=0 ;i--) {
                UserRideInfo uri = userRideInfos.get(i);
                User user = uri.getUser();
                user.getUserRideInfos().remove(uri);
                userService.saveUser(user);
                ride.getUserRideInfos().remove(uri);
                saveRide(ride);
                userRideInfoRepository.delete(uri);
            }
            //remove rideRequests from users and delete
            List<RideRequest> rideRequests = ride.getRideRequests();
            for(int i =rideRequests.size()-1; i>=0 ;i--) {
                RideRequest rideRequest = rideRequests.get(i);
                User user = rideRequest.getUser();
                user.getRideRequests().remove(rideRequest);
                userService.saveUser(user);
                ride.getRideRequests().remove(rideRequest);
                saveRide(ride);
                rideRequestRepository.delete(rideRequest);
            }
            //romove from car
            Car car  = ride.getChosenCar();
            car.getRides().remove(ride);
            carService.updateCar(car);
            //delete ride
            rideRepository.delete(rideId);
        } catch (Exception e) {
            throw new RideServiceException("Ride not deleted");
        }
    }

    private Location saveLocation(Location location) throws RideServiceException {
        try {
            return locationRepository.save(location);
        } catch (Exception e) {
            throw new RideServiceException("Location not saved");
        }
    }

    private User saveUser(User user) {
        return userService.saveUser(user);
    }

    //save UserRideInfo for creator that marks him as driver
    private UserRideInfo creatorUserRideInfo(User creator, Ride ride) {
        return userRideInfoRepository.save(new UserRideInfo(true, creator, ride));
    }
}

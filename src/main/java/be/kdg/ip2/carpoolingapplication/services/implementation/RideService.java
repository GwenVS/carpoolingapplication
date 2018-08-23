package be.kdg.ip2.carpoolingapplication.services.implementation;

import be.kdg.ip2.carpoolingapplication.domain.*;
import be.kdg.ip2.carpoolingapplication.domain.locations.EndLocation;
import be.kdg.ip2.carpoolingapplication.domain.locations.Location;
import be.kdg.ip2.carpoolingapplication.domain.locations.RideLocation;
import be.kdg.ip2.carpoolingapplication.domain.locations.StartLocation;
import be.kdg.ip2.carpoolingapplication.domain.user.User;
import be.kdg.ip2.carpoolingapplication.domain.user.UserRideInfo;
import be.kdg.ip2.carpoolingapplication.repositories.LocationRepository;
import be.kdg.ip2.carpoolingapplication.repositories.RideRepository;
import be.kdg.ip2.carpoolingapplication.repositories.SubRideRepository;
import be.kdg.ip2.carpoolingapplication.repositories.UserRideInfoRepository;
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

    private RideRepository rideRepository;
    private SubRideRepository subRideRepository;
    private LocationRepository locationRepository;
    private UserRideInfoRepository userRideInfoRepository;


    @Autowired
    public RideService(IUserService userService, RideRepository rideRepository, SubRideRepository subRideRepository, LocationRepository locationRepository, UserRideInfoRepository userRideInfoRepository) {
        this.userService = userService;
        this.rideRepository = rideRepository;
        this.subRideRepository = subRideRepository;
        this.locationRepository = locationRepository;
        this.userRideInfoRepository = userRideInfoRepository;
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
            List<SubRide> subrides = createSubRides(r);
            r.setSubRides(subrides);
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

    private SubRide saveSubRide(SubRide subRide) throws RideServiceException {
        try {
            return subRideRepository.save(subRide);
        } catch (Exception e) {
            throw new RideServiceException("SubRide not saved");
        }
    }

    @Override
    public void deleteRide(Long rideId) throws RideServiceException {

        //todo: verder afwerken
        try {
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

    //create a list of subrides from given locations
    private List<SubRide> createSubRides(Ride ride) throws RideServiceException {
        List<RideLocation> locations = ride.getLocations();
        for (RideLocation loc : locations) {
            loc.setRide(ride);
            saveLocation(loc);
        }
        List<SubRide> subRides = new ArrayList<>();
        //loop until 1 before last of locations:
        //a subride contains a begin and endlocation
        // so there is always 1 less subride then locations in the list
        for (int i = 0; i < locations.size() - 1; i++) {
            SubRide subRide = new SubRide();
            subRide.setRide(ride);
            subRideRepository.save(subRide);
            StartLocation sl = new StartLocation(locations.get(i).getLatitude(), locations.get(i).getLongitude(), subRide);
            locationRepository.save(sl);
            subRide.setStartLocation(sl);
            EndLocation el = new EndLocation(locations.get(i + 1).getLatitude(), locations.get(i + 1).getLongitude(), subRide);
            subRide.setEndLocation(el);
            saveSubRide(subRide);
            subRides.add(subRide);
        }
        return subRides;
    }
}

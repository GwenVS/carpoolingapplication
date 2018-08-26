package be.kdg.ip2.carpoolingapplication.services.implementation;

import be.kdg.ip2.carpoolingapplication.domain.Ride;
import be.kdg.ip2.carpoolingapplication.domain.RideRequest;
import be.kdg.ip2.carpoolingapplication.domain.user.User;
import be.kdg.ip2.carpoolingapplication.domain.user.UserRideInfo;
import be.kdg.ip2.carpoolingapplication.repositories.RideRequestRepository;
import be.kdg.ip2.carpoolingapplication.services.declaration.IRideRequestService;
import be.kdg.ip2.carpoolingapplication.services.declaration.IRideService;
import be.kdg.ip2.carpoolingapplication.services.declaration.IUserService;
import be.kdg.ip2.carpoolingapplication.services.exceptions.RideServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RideRequestService implements IRideRequestService {

    private IUserService userService;
    private IRideService rideService;

    private RideRequestRepository rideRequestRepository;

    @Autowired
    public RideRequestService(IUserService userService, IRideService rideService, RideRequestRepository rideRequestRepository) {
        this.userService = userService;
        this.rideService = rideService;
        this.rideRequestRepository = rideRequestRepository;
    }


    @Override
    public RideRequest createRideRequest(Long rideId, String username, RideRequest rideRequest) throws RideServiceException {
        try {
            User user = userService.getUserByUsername(username);
            Ride ride = rideService.getRideById(rideId);
            rideRequest.setRide(ride);
            rideRequest.setUser(user);
            return rideRequestRepository.save(rideRequest);
        } catch (Exception e) {
            throw new RideServiceException("RideRequest not saved: " + e.getMessage());
        }

    }

    @Override
    public List<RideRequest> getRideRequestsForRidesOfUser(String username) {
        User user = userService.getUserByUsername(username);
        List<UserRideInfo> uris = user.getUserRideInfos();
        List<Ride> rides = new ArrayList<>();
        for (UserRideInfo uri : uris) {
            if (uri.getIsDriver()) {
                rides.add(uri.getRide());
            }
        }
        List<RideRequest> rideRequests = new ArrayList<>();
        for (Ride ride : rides) {
            rideRequests.addAll(rideRequestRepository.findAllByRide_RideId(ride.getRideId()));
        }
        return rideRequests;
    }

    @Override
    public  List<RideRequest> getUserRideRequests(String username){
        User user = userService.getUserByUsername(username);
        return user.getRideRequests();
    }
}

package be.kdg.ip2.carpoolingapplication.services.declaration;

import be.kdg.ip2.carpoolingapplication.domain.RideRequest;
import be.kdg.ip2.carpoolingapplication.services.exceptions.RideServiceException;

import java.util.List;

public interface IRideRequestService {

    RideRequest createRideRequest(Long rideId, String username, RideRequest rideRequest) throws RideServiceException;

    List<RideRequest> getRideRequestsForRidesOfUser(String username);

    List<RideRequest> getUserRideRequests(String username);
}

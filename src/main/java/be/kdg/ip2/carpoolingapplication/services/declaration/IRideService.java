package be.kdg.ip2.carpoolingapplication.services.declaration;

import be.kdg.ip2.carpoolingapplication.domain.Ride;
import be.kdg.ip2.carpoolingapplication.services.exceptions.RideServiceException;

import java.time.LocalDateTime;
import java.util.List;

public interface IRideService {
    List<Ride> getAllRides();

    //create new ride attached to a user
    Ride createRide(Long userId, Ride ride) throws RideServiceException;

    Ride getRideById(long rideId) throws RideServiceException;

    List<Ride> getRidesByDepartureTime(LocalDateTime minDepartureTime, LocalDateTime maxDepartureTime);

    Ride saveRide(Ride ride1)throws RideServiceException;
}

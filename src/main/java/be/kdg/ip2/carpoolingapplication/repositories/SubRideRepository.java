package be.kdg.ip2.carpoolingapplication.repositories;

import be.kdg.ip2.carpoolingapplication.domain.Ride;
import be.kdg.ip2.carpoolingapplication.domain.SubRide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubRideRepository extends JpaRepository<SubRide, Long> {
    List<SubRide> findSubRideByStartLocation_LatitudeAndStartLocation_Longitude(double latitude, double longitude);
    List<SubRide> findSubRideByRide(Ride ride);
}

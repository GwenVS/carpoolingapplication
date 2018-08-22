package be.kdg.ip2.carpoolingapplication.repositories;
import be.kdg.ip2.carpoolingapplication.domain.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findRidesByDepartureTimeOutwardJourneyBetween(LocalDateTime minDepartureTime, LocalDateTime maxDepartureTime);
    List<Ride> findRidesByDepartureTimeReturnTripBetween(LocalDateTime minDepartureTime, LocalDateTime maxDepartureTime);
}

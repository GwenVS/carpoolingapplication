package be.kdg.ip2.carpoolingapplication.repository.declaration;

import be.kdg.ip2.carpoolingapplication.domain.Ride;
import be.kdg.ip2.carpoolingapplication.domain.user.UserRideInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRideInfoRepository extends JpaRepository<UserRideInfo, Long> {
    List<UserRideInfo> findUserRideInfosByRide(Ride ride);
    List<UserRideInfo> findUserRideInfosByUser_Username(String username);
}

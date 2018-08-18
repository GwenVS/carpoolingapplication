package be.kdg.ip2.carpoolingapplication.repository.declaration;

import be.kdg.ip2.carpoolingapplication.domain.user.UserRideInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRideInfoRepository extends JpaRepository<UserRideInfo, Long> {
}

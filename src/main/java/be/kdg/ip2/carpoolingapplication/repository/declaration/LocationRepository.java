package be.kdg.ip2.carpoolingapplication.repository.declaration;

import be.kdg.ip2.carpoolingapplication.domain.locations.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}

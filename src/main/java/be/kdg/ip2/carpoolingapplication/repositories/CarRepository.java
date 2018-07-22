package be.kdg.ip2.carpoolingapplication.repositories;
import be.kdg.ip2.carpoolingapplication.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}

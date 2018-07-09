package be.kdg.ip2.carpoolingapplication.repositories;
import be.kdg.ip2.carpoolingapplication.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository  extends JpaRepository<Car, Integer> {
}

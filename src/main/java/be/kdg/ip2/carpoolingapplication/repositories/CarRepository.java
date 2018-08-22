package be.kdg.ip2.carpoolingapplication.repositories;

import be.kdg.ip2.carpoolingapplication.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findCarsByUser_Username(String username);
}

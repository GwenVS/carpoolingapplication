package be.kdg.ip2.carpoolingapplication.services.declaration;

import be.kdg.ip2.carpoolingapplication.domain.Car;

import java.util.List;

public interface ICarService {
    Car getCarById(Long carId);

    List<Car> getCarsByUsername(String username);

    Car createCar(String username, Car car);

    void deleteCar(Long carId);

    Car updateCar(Car car);
}

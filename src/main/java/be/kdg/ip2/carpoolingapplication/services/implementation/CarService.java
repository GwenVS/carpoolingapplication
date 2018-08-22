package be.kdg.ip2.carpoolingapplication.services.implementation;

import be.kdg.ip2.carpoolingapplication.domain.Car;
import be.kdg.ip2.carpoolingapplication.repositories.CarRepository;
import be.kdg.ip2.carpoolingapplication.services.declaration.ICarService;
import be.kdg.ip2.carpoolingapplication.services.exceptions.CarServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService implements ICarService {
    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car getCarById(Long carId){
        try{
            return carRepository.getOne(carId);
        } catch (Exception e){
            throw new CarServiceException("@CarService: car "+ carId + " not found");
        }
    }

    @Override
    public List<Car> getCarsByUsername(String username){
        List<Car> cars = carRepository.findCarsByUser_Username(username);
        if (cars == null) {
            return new ArrayList<>();
        } else {
            return cars;
        }
    }

    @Override
    public Car createCar(Car car){
        try {
            return carRepository.save(car);
        } catch (Exception e){
            throw new CarServiceException("@CarService: car not saved: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteCar(Long carId) {
        try {
            carRepository.delete(carId);
        } catch (Exception e){
            throw new CarServiceException("@CarService: car not deleted: " + e.getMessage(), e);
        }
    }
}

package be.kdg.ip2.carpoolingapplication.services.implementation;

import be.kdg.ip2.carpoolingapplication.domain.Car;
import be.kdg.ip2.carpoolingapplication.domain.Ride;
import be.kdg.ip2.carpoolingapplication.domain.user.User;
import be.kdg.ip2.carpoolingapplication.repositories.CarRepository;
import be.kdg.ip2.carpoolingapplication.services.declaration.ICarService;
import be.kdg.ip2.carpoolingapplication.services.declaration.IRideService;
import be.kdg.ip2.carpoolingapplication.services.declaration.IUserService;
import be.kdg.ip2.carpoolingapplication.services.exceptions.CarServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService implements ICarService {

    private CarRepository carRepository;
    private IUserService userService;
    private IRideService rideService;

    @Autowired
    public CarService(CarRepository carRepository, UserService userService, RideService rideService) {
        this.carRepository = carRepository;
        this.userService = userService;
        this.rideService = rideService;
    }

    @Override
    public Car getCarById(Long carId) throws CarServiceException{
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
    public Car createCar(String username, Car car) throws CarServiceException{
        try {
            car.setUser(userService.getUserByUsername(username));
            return carRepository.save(car);
        } catch (Exception e){
            throw new CarServiceException("@CarService: car not saved: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteCar(Long carId) throws CarServiceException {
        try {
            Car car = getCarById(carId);
            User user = car.getUser();
            user.getCars().remove(car);
            userService.saveUser(user);
            carRepository.delete(carId);
        } catch (Exception e){
            throw new CarServiceException("@CarService: car not deleted: " + e.getMessage(), e);
        }
    }

    @Override
    public Car updateCar(Car car) throws CarServiceException {
        try {
            return carRepository.save(car);
        } catch (Exception e){
            throw new CarServiceException("@CarService: car not saved: " + e.getMessage(), e);
        }
    }
}

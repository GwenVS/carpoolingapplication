package be.kdg.ip2.carpoolingapplication.controllers;
import be.kdg.ip2.carpoolingapplication.domain.Car;
import be.kdg.ip2.carpoolingapplication.services.declaration.ICarService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://carpoolingapplicationfe.herokuapp.com")
public class CarController {
    private static final Logger logger = LogManager.getLogger(CarController.class);

    private ICarService carService;

    @Autowired
    public CarController(ICarService carService) {
        this.carService = carService;
    }

    @GetMapping("api/public/cars/user/{username}")
    public List<Car> getAllCarsByUser(@PathVariable("username") String username){
        return carService.getCarsByUsername(username);
    }

    @GetMapping("api/public/cars/{car_id}")
    public Car getCarById(@PathVariable("car_id") Long carId){
        return carService.getCarById(carId);
    }
}

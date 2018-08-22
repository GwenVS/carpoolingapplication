package be.kdg.ip2.carpoolingapplication.controllers;
import be.kdg.ip2.carpoolingapplication.domain.Car;
import be.kdg.ip2.carpoolingapplication.domain.user.User;
import be.kdg.ip2.carpoolingapplication.services.declaration.ICarService;
import be.kdg.ip2.carpoolingapplication.services.declaration.IUserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://carpoolingapplicationfe.herokuapp.com")
public class CarController {
    private static final Logger logger = LogManager.getLogger(CarController.class);

    private ICarService carService;
    private IUserService userService;

    //todo: exceptions opvangen
    @Autowired
    public CarController(ICarService carService, IUserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    @GetMapping("api/public/cars/user/{username}")
    //@GetMapping("/api/private/cars/user/{username}")
    //@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public List<Car> getAllCarsByUser(@PathVariable("username") String username){
        return carService.getCarsByUsername(username);
    }

    @GetMapping("api/public/cars/{car_id}")
    public Car getCarById(@PathVariable("car_id") Long carId){
        return carService.getCarById(carId);
    }

    @PostMapping("api/public/cars/user/{username}")
    //@PostMapping("/api/private/cars/user/{username}")
    //@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity createCar(@PathVariable String username, @RequestBody Car car){
        car.setUser(userService.findUserByUsername(username));
        return ResponseEntity.ok(carService.createCar(car));
    }

    @PutMapping("api/public/cars/{car_id}")
    public ResponseEntity updateCar(@PathVariable Long car_id, @RequestBody Car car) {
        Car updatedCar = carService.getCarById(car_id);
        updatedCar.setConsumption(car.getConsumption());
        updatedCar.setMaxAmountPassengers(car.getMaxAmountPassengers());
        updatedCar.setType(car.getType());
        return ResponseEntity.ok(carService.createCar(updatedCar));
    }

    @DeleteMapping("api/public/cars/{car_id}")
    public ResponseEntity deleteCar(@PathVariable Long car_id){
        Car car = carService.getCarById(car_id);
        User user = car.getUser();
        user.getCars().remove(car);
        userService.saveUser(user);
        carService.deleteCar(car_id);
        return ResponseEntity.ok().build();
    }
}

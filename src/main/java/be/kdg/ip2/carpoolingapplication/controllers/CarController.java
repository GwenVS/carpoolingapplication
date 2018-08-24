package be.kdg.ip2.carpoolingapplication.controllers;
import be.kdg.ip2.carpoolingapplication.domain.Car;
import be.kdg.ip2.carpoolingapplication.services.declaration.ICarService;
import be.kdg.ip2.carpoolingapplication.services.exceptions.CarServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://carpoolingapplicationfe.herokuapp.com")
public class CarController {
    private static final Logger logger = LogManager.getLogger(CarController.class);

    private ICarService carService;

    //todo: exceptions opvangen
    @Autowired
    public CarController(ICarService carService) {
        this.carService = carService;
    }

    @GetMapping("api/public/cars/user/{username}")
    //@GetMapping("/api/private/cars/user/{username}")
    //@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public List<Car> getAllCarsByUser(@PathVariable("username") String username){
        return carService.getCarsByUsername(username);
    }

    @PostMapping("api/public/cars/user/{username}")
    //@PostMapping("/api/private/cars/user/{username}")
    //@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity createCar(@PathVariable String username, @RequestBody Car car){
        try {
            return ResponseEntity.ok(carService.createCar(username, car));
        } catch (CarServiceException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CarServiceException: car not created: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong while creating your car. Try again later");
        }
    }

    @PutMapping("api/public/cars/{car_id}")
    public ResponseEntity updateCar(@PathVariable Long car_id, @RequestBody Car car) {
        try {
            Car updatedCar = carService.getCarById(car_id);
            updatedCar.setConsumption(car.getConsumption());
            updatedCar.setMaxAmountPassengers(car.getMaxAmountPassengers());
            updatedCar.setType(car.getType());
            return ResponseEntity.ok(carService.updateCar(updatedCar));
        } catch (CarServiceException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CarServiceException: car not updated: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong while updating your car. Try again later");
        }
    }

    @DeleteMapping("api/public/cars/{car_id}")
    public ResponseEntity deleteCar(@PathVariable Long car_id){
        try {
            carService.deleteCar(car_id);
            return ResponseEntity.ok().build();
        } catch (CarServiceException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CarServiceException: car not deleted: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong while deleting your car. Try again later");
        }

    }
}

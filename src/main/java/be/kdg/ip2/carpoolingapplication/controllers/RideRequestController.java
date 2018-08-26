package be.kdg.ip2.carpoolingapplication.controllers;

import be.kdg.ip2.carpoolingapplication.domain.RideRequest;
import be.kdg.ip2.carpoolingapplication.services.declaration.IRideRequestService;
import be.kdg.ip2.carpoolingapplication.services.exceptions.RideServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://carpoolingapplicationfe.herokuapp.com")
public class RideRequestController {
    private static final Logger logger = LogManager.getLogger(RideRequestController.class);
    private static final String RIDEREQUEST_URL = "/api/public/riderequests";

    private IRideRequestService rideRequestService;

    @Autowired
    public RideRequestController(IRideRequestService rideRequestService) {
        this.rideRequestService = rideRequestService;
    }


    /**
     * get all riderequests for rides where user is creator
     * @param username
     * @return
     */
    @GetMapping(RIDEREQUEST_URL + "/user/rides/{username}")
    public List<RideRequest> getRideRequestsForMyRides(@PathVariable String username) {
        return rideRequestService.getRideRequestsForRidesOfUser(username);
    }

    /**
     * get a users own riderequests
     * @param username
     * @return
     */
    @GetMapping(RIDEREQUEST_URL + "/user/{username}")
    public List<RideRequest> getUserRideRequests(@PathVariable String username) {
        return rideRequestService.getUserRideRequests(username);
    }

    /**
     * create a rideRequest to join a ride
     * @param ride_id
     * @param username
     * @param rideRequest
     * @return
     */
    @PostMapping(RIDEREQUEST_URL + "/ride/{ride_id}/user/{username}")
    public ResponseEntity createRideRequest(@PathVariable Long ride_id, @PathVariable String username, @RequestBody RideRequest rideRequest) {
        try {
            RideRequest createdRideRequest = rideRequestService.createRideRequest(ride_id, username, rideRequest);
            logger.info("@RidesController: new rideRequest created.");
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRideRequest);
        } catch (RideServiceException e) {
            logger.error("@RidesController: error while creating new ride: " + rideRequest.toString() + "error: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("RideServiceException: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong while creating your ride. Try again later");
        }
    }
}

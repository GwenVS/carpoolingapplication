package be.kdg.ip2.carpoolingapplication.controllers;
import be.kdg.ip2.carpoolingapplication.domain.Ride;
import be.kdg.ip2.carpoolingapplication.domain.user.User;
import be.kdg.ip2.carpoolingapplication.services.declaration.IAuthenticationHelperService;
import be.kdg.ip2.carpoolingapplication.services.declaration.IUserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Rest controller that handles all actions concerning a user
 */

@RestController
@CrossOrigin(origins = "https://carpoolingapplicationfe.herokuapp.com")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);
    private final IUserService userService;
    private final IAuthenticationHelperService authenticationHelperService;

    @Autowired
    public UserController(IUserService userService, IAuthenticationHelperService authenticationHelperService) {
        this.userService = userService;
        this.authenticationHelperService = authenticationHelperService;
    }

    //GET ALL USERS
    @GetMapping("/api/private/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getUsers(){
        logger.info("@UserRestController: searching all users.");
        return userService.findUsers();
    }

    //GET ALL INFO ABOUT ALL USERS (LIMITED)
    @GetMapping("/api/private/users/limited")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getAllUsersLimited(HttpServletRequest request){
        List<User> users = userService.findUsers();
        logger.info("@UserRestController: searching all userinfo.");
        return ResponseEntity.ok(users);
    }

    //GET ONE USER ON USERID
    @GetMapping("/api/private/userid/{userId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> getUser(HttpServletRequest request, @PathVariable long userId){
        User requestUser = userService.findUserById(userId);
        String username = "";
        if (requestUser != null) username = requestUser.getUsername();

        if (!authenticationHelperService.userIsAllowedToAccessResource(request, username)){
            logger.info("@UserRestController: unauthorized request for user " + username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        };
        if(requestUser == null){
            logger.error("@UserRestController: user not found.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        logger.info("@UserRestController: user found.");
        return ResponseEntity.ok().body(requestUser);
    }


    //GET ONE USER ON USERNAME
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/api/private/users/username/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username, HttpServletRequest request){
        User requestUser = userService.findUserByUsername(username);
        if (!authenticationHelperService.userIsAllowedToAccessResource(request, username)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if(requestUser == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok().body(requestUser);
    }


    //UPDATE USER
    @PutMapping("/api/private/users/{username}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<User> updateUser(@PathVariable String username, @Valid @RequestBody User changedUser, HttpServletRequest request){
        String usernameFromToken = (String) request.getAttribute("username");
        User requestUser = userService.findUserByUsername(username);
        if (!authenticationHelperService.userIsAllowedToAccessResource(request, username)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        };
        if(requestUser == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        if(!requestUser.getUsername().equalsIgnoreCase(changedUser.getUsername()) ||
                !requestUser.getUsername().equalsIgnoreCase(usernameFromToken) ||
                !changedUser.getUsername().equalsIgnoreCase(usernameFromToken)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        //
        requestUser.setFirstName(changedUser.getFirstName());
        requestUser.setLastName(changedUser.getLastName());
        requestUser.setGender(changedUser.getGender());
        requestUser.setBirthday(changedUser.getBirthday());
        User savedUser = userService.updateUserNoPassword(requestUser);
        return ResponseEntity.ok(savedUser);
    }

    //UPDATE PASSWORD
    @PostMapping("/api/private/users/{username}/updatepassword")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity updatePasswordOfUser(@PathVariable String username, @RequestBody User changedUser, HttpServletRequest request){
        User requestUser = userService.findUserByUsername(username);

        if (!authenticationHelperService.userIsAllowedToAccessResource(request, username)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        };

        if(requestUser == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        requestUser.setPassword(changedUser.getPassword());

        userService.updateUser(requestUser.getUserId(), requestUser);

        return ResponseEntity.ok().build();
    }


    //TODO DELETE USER


    @GetMapping("/api/public/user/{username}/rides")
    public ResponseEntity getRidesByUser(@PathVariable String username) {
        try {
            List<Ride> rides = userService.getRidesByUsername(username);
            logger.info("@RidesController: fetched Rides for user " + username);
            return ResponseEntity.status(HttpStatus.CREATED).body(rides);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong while creating your ride. Try again later");
        }
    }
}

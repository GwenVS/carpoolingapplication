package be.kdg.ip2.carpoolingapplication.controllers;

import be.kdg.ip2.carpoolingapplication.dto.UserDto;
import be.kdg.ip2.carpoolingapplication.domain.user.UserTokenState;
import be.kdg.ip2.carpoolingapplication.security.auth.JwtAuthenticationRequest;
import be.kdg.ip2.carpoolingapplication.services.declaration.AuthenticationHelperService;
import be.kdg.ip2.carpoolingapplication.services.exceptions.CustomAuthenticationException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Rest controller that handles the login and registration of users
 */

@RestController
@CrossOrigin(origins = "https://carpoolingapplication.herokuapp.com")
public class AuthenticationController {
    private static final Logger logger = LogManager.getLogger(UserRestController.class);
    private final AuthenticationHelperService authenticationHelperService;

    @Autowired
    public AuthenticationController(AuthenticationHelperService authenticationHelperService) {
        this.authenticationHelperService = authenticationHelperService;
    }

    @PostMapping("/api/public/login")
    public ResponseEntity createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response, Device device){
        try{
            UserTokenState userTokenState = authenticationHelperService.authenticate(authenticationRequest, device);
            return ResponseEntity.ok(userTokenState);
        }catch (CustomAuthenticationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/api/public/register")
    public ResponseEntity register(@RequestBody UserDto userDto){
        try{
            authenticationHelperService.register(userDto);
            return ResponseEntity.ok().build();
        }catch (CustomAuthenticationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong while registering. Try again later");
        }

    }

}

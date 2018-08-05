package be.kdg.ip2.backend.controller.rest;

import be.kdg.ip2.backend.dto.UserDto;
import be.kdg.ip2.backend.domain.user.UserTokenState;
import be.kdg.ip2.backend.security.auth.JwtAuthenticationRequest;
import be.kdg.ip2.backend.service.declaration.AuthenticationHelperService;
import be.kdg.ip2.backend.service.exception.CustomAuthenticationException;
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
@CrossOrigin(origins = "http://localhost:9090")
public class AuthenticationController {
    private final Logger logger = Logger.getLogger(UserRestController.class);
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

package be.kdg.ip2.carpoolingapplication.services.implementation;

import be.kdg.ip2.carpoolingapplication.domain.user.User;
import be.kdg.ip2.carpoolingapplication.security.UserTokenState;
import be.kdg.ip2.carpoolingapplication.security.JwtAuthenticationRequest;
import be.kdg.ip2.carpoolingapplication.services.exceptions.CustomAuthenticationException;
import be.kdg.ip2.carpoolingapplication.security.TokenHelper;
import be.kdg.ip2.carpoolingapplication.services.declaration.IAuthenticationHelperService;
import be.kdg.ip2.carpoolingapplication.services.declaration.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Service class for user authentication
 */

@Service
@Primary
public class AuthenticationHelperService implements IAuthenticationHelperService {

    private IUserService userService;
    private TokenHelper tokenHelper;
    private AuthenticationManager authenticationManager;
    private CustomUserDetailsService userDetailsService;


    @Autowired
    public AuthenticationHelperService(IUserService userService, TokenHelper tokenHelper, AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService) {
        this.userService = userService;
        this.tokenHelper = tokenHelper;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public UserTokenState authenticate(JwtAuthenticationRequest authenticationRequest, Device device){

        try{
            // Use the username and password and place it in a secure UsernamePasswordAuthenticationToken. This object can then be used to authenticate a user.
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );

            // Place the rights of the user in the context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //Retrieve the user information and convert it to a user
            User user = (User)authentication.getPrincipal();

            //Fill in the roles of a user
            StringBuilder roles = new StringBuilder();
            user.getAuthorities().forEach(role -> roles.append(role.getAuthority() + " "));

            //Generate a token for a user
            String jws = tokenHelper.generateToken(user.getUsername(), device, roles.toString());
            int expiresIn = tokenHelper.getExpiredIn();

            //Wrap the state of the token in custom wrapper clas
            return new UserTokenState(jws, expiresIn);

        }catch (AuthenticationException e){
            if (e.getMessage().toLowerCase().contains("bad credentials")) throw new CustomAuthenticationException("Username and password don't match");
            throw new CustomAuthenticationException("Something went wrong while trying to login", e);
        }
    }

    @Override
    public boolean checkUsernameCredentials(String username){
        return userService.usernameUsed(username);
    }

    @Override
    public boolean checkEmailCredentials(String email){
        return userService.emailUsed(email);
    }

    @Override
    public boolean register(User user){
        //Check if username or email are already used
        boolean usernameGood = this.checkUsernameCredentials(user.getUsername());
        boolean emailGood = this.checkEmailCredentials(user.getEmail());

        if(!usernameGood && !emailGood){
            throw new CustomAuthenticationException("Username and email already used!");
        }
        else if(!usernameGood){
            throw new CustomAuthenticationException("Username is already used!");
        }
        else if(!emailGood){
            throw new CustomAuthenticationException("Email is already used!");
        }
        else{
            userDetailsService.addUser(user);
            return true;
        }
    }

    @Override
    public boolean isAdmin(User user) {
        for(GrantedAuthority authority : user.getAuthorities()){
            if(authority.getAuthority().equalsIgnoreCase("ROLE_ADMIN")){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean credentialsMatchToken(HttpServletRequest request) {
        String usernameToken = getUsernameFromTokem(request);
        User tokenUser = userService.getUserByUsername(usernameToken);
        return false;
    }

    @Override
    public boolean userIsAllowedToAccessResource(HttpServletRequest request, String usernameClaimedUser) {
        //usernameClaimedUser is the username that was provided through the url
        String usernameToken = getUsernameFromTokem(request);
        User tokenUser = userService.getUserByUsername(usernameToken);

        //Check whether or not the user is an admin
        //(this is secure because we take the user found in the token)
        boolean isAdmin = isAdmin(tokenUser);

        //When an admin makes a request it is allowed
        //When it's not an admin, then the username from the path has to be the same as the one from the token
        if(!isAdmin && !tokenUser.getUsername().equalsIgnoreCase(usernameClaimedUser)){
            return false;
        }

        return true;
    }

    private String getUsernameFromTokem(HttpServletRequest request){
        return (String) request.getAttribute("username");
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public void setTokenHelper(TokenHelper tokenHelper) {
        this.tokenHelper = tokenHelper;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void setUserDetailsService(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}

package be.kdg.ip2.carpoolingapplication.services.declaration;

import be.kdg.ip2.carpoolingapplication.domain.user.User;
import be.kdg.ip2.carpoolingapplication.domain.user.UserTokenState;
import be.kdg.ip2.carpoolingapplication.security.auth.JwtAuthenticationRequest;
import be.kdg.ip2.carpoolingapplication.services.exceptions.CustomAuthenticationException;
import org.springframework.mobile.device.Device;

import javax.servlet.http.HttpServletRequest;

public interface IAuthenticationHelperService {
    UserTokenState authenticate(JwtAuthenticationRequest authenticationRequest, Device device) throws CustomAuthenticationException;

    boolean isAdmin(User user);

    boolean credentialsMatchToken(HttpServletRequest request);

    boolean userIsAllowedToAccessResource(HttpServletRequest request, String usernameClaimedUser);

    boolean checkUsernameCredentials(String username);

    boolean checkEmailCredentials(String email);

    boolean register(User user) throws CustomAuthenticationException;
}

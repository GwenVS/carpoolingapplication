package be.kdg.ip2.backend.service.declaration;

import be.kdg.ip2.backend.domain.user.User;
import be.kdg.ip2.backend.domain.user.UserTokenState;
import be.kdg.ip2.backend.dto.UserDto;
import be.kdg.ip2.backend.security.auth.JwtAuthenticationRequest;
import be.kdg.ip2.backend.service.exception.CustomAuthenticationException;
import org.springframework.mobile.device.Device;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationHelperService {
    UserTokenState authenticate(JwtAuthenticationRequest authenticationRequest, Device device) throws CustomAuthenticationException;

    boolean isAdmin(User user);

    boolean credentialsMatchToken(HttpServletRequest request);

    boolean userIsAllowedToAccessResource(HttpServletRequest request, String usernameClaimedUser);

    boolean checkUsernameCredentials(String username);

    boolean checkEmailCredentials(String email);

    boolean register(UserDto userDto) throws CustomAuthenticationException;
}

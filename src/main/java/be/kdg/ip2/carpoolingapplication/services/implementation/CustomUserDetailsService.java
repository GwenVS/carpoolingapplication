package be.kdg.ip2.carpoolingapplication.services.implementation;

import be.kdg.ip2.carpoolingapplication.domain.user.Authority;
import be.kdg.ip2.carpoolingapplication.domain.user.User;
import be.kdg.ip2.carpoolingapplication.repositories.UserRepository;
import be.kdg.ip2.carpoolingapplication.services.exceptions.UserServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LogManager.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return user;
        }
    }

    public void changePassword(String oldPassword, String newPassword) {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();

        if (authenticationManager != null) {
            logger.debug("Re-authenticating user '"+ username + "' for password change request.");

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
        } else {
            logger.debug("No authentication manager set. can't change Password!");

            return;
        }

        logger.debug("Changing password for user '"+ username + "'");

        User user = (User) loadUserByUsername(username);

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

    }

    public boolean checkLogin(String username, String password) throws UserServiceException {
        User u = userRepository.findUserByUsername(username);
        if(u == null || !passwordEncoder.matches(password, u.getPassword())){
            return false;
        }
        return true;
    }

    public User saveUser(User user) throws UserServiceException {
        User u = userRepository.save(user);
        if (u == null)
            throw new UserServiceException("User not saved");
        return u;
    }

    public User addUser(User user) throws UserServiceException {
        Authority authority = new Authority();
        authority.setName("ROLE_USER");
        authority.setUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(Arrays.asList(authority));
        return this.saveUser(user);
    }

    public User updateUser(){

        return null;
    }

}

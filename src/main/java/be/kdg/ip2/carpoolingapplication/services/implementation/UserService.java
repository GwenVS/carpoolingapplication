package be.kdg.ip2.carpoolingapplication.services.implementation;

import be.kdg.ip2.carpoolingapplication.domain.Ride;
import be.kdg.ip2.carpoolingapplication.domain.user.Authority;
import be.kdg.ip2.carpoolingapplication.domain.user.User;
import be.kdg.ip2.carpoolingapplication.domain.user.UserRideInfo;
import be.kdg.ip2.carpoolingapplication.repositories.UserRepository;
import be.kdg.ip2.carpoolingapplication.services.declaration.IUserService;
import be.kdg.ip2.carpoolingapplication.services.exceptions.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@Primary
public class UserService implements IUserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserById(Long id) throws UserServiceException {
        User user = userRepository.findOne(id);
        if (user == null)
            throw new UserServiceException("User not found");
        return user;
    }

    @Override
    public List<User> findUsers() {
        List<User> users = userRepository.findAll();
        if(users == null){
            return new ArrayList<>();
        }
        else{
            return users;
        }
    }

    @Override
    public User getUserByUsername(String username) throws UserServiceException {
        User user = userRepository.findUserByUsername(username);
        if (user == null)
            throw new UserServiceException("User not found");

        return user;
    }

    @Override
    public User saveUser(User user) throws UserServiceException {
        User u = userRepository.save(user);
        if (u == null)
            throw new UserServiceException("User not saved");
        return u;
    }

    //IMPORTANT ONLY USE THIS METHOD IF YOU WANT TO ALSO UPDATE THE PASSWORD!!!
    @Override
    public User updateUser(Long userId, User user) throws UserServiceException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.saveUser(user);
    }

    @Override
    public User updateUserNoPassword(User user) throws UserServiceException {
        return this.saveUser(user);
    }

    @Override
    public User addUser(User user) throws UserServiceException {
        Authority authority = new Authority();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(Arrays.asList(authority));
        authority.setUser(user);
        return this.saveUser(user);
    }

    @Override
    public void deleteUser(Long userId) throws UserServiceException {
        User user = userRepository.findOne(userId);
        if (user == null)
            throw new UserServiceException("User not found");

        userRepository.delete(user);
    }

    @Override
    public void checkLogin(Long userId, String currentPassword) throws UserServiceException {
        User u = userRepository.findOne(userId);
        if (u == null || !passwordEncoder.matches(currentPassword, u.getPassword())) {
            throw new UserServiceException(("Username or password are wrong for user " + userId));
        }
    }

    @Override
    public void updatePassword(Long userId, String oldPassword, String newPassword) throws UserServiceException {
        User u = userRepository.findOne(userId);
        checkLogin(userId, oldPassword);
        u.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(u);
    }

    @Override
    public User findUserByEmail(String email) throws UserServiceException {
        User user = userRepository.findUserByEmail(email);
        if (user == null)
            throw new UserServiceException("User not found");
        return user;
    }

    @Override
    public boolean usernameUsed(String username) {
        User user = userRepository.findUserByUsername(username);
        return user == null;
    }

    @Override
    public boolean emailUsed(String email) {
        User user = userRepository.findUserByEmail(email);
        return user == null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findUserByUsername(username);
        if (u == null) throw new UsernameNotFoundException("No such user: " + username);
        return u;
    }

    @Override
    public void addUserRideInfo(Long id, UserRideInfo userRideInfo) {
        User user = userRepository.findOne(id);
        user.addUserRideInfo(userRideInfo);
        this.updateUserNoPassword(user);
    }

    @Override
    public User updateUserInformation(Long id, User user) throws UserServiceException {
        User u = userRepository.findOne(id);
        return null;
    }
}

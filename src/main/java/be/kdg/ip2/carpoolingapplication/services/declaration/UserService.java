package be.kdg.ip2.carpoolingapplication.services.declaration;

import be.kdg.ip2.carpoolingapplication.domain.user.User;
import be.kdg.ip2.carpoolingapplication.domain.user.UserRideInfo;
import be.kdg.ip2.carpoolingapplication.services.exceptions.UserServiceException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{

    User findUserById(Long id) throws UserServiceException;

    User findUserByEmail(String email) throws UserServiceException;

    List<User> findUsers();

    User findUserByUsername(String username) throws UserServiceException;

    List<User> findUsersByRole(Class c);

    User saveUser(User user) throws UserServiceException;

    User updateUser(Long userId, User user) throws UserServiceException;

    User updateUserInformation(Long is, User user) throws UserServiceException;

    User addUser(User user) throws UserServiceException;

    void deleteUser(Long userId) throws UserServiceException;

    void checkLogin(Long userId, String currentPassword) throws UserServiceException;

    void updatePassword(Long userId, String oldPassword, String newPassword) throws UserServiceException;

    void addUserRideInfo(Long id, UserRideInfo userRideInfo);

    User updateUserNoPassword(User user) throws UserServiceException;

    boolean usernameUsed(String username);

    boolean emailUsed(String email);

}

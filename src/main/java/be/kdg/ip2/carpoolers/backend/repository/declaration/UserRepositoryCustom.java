package be.kdg.ip2.carpoolers.backend.repository.declaration;

import be.kdg.ip2.carpoolers.backend.domain.user.User;

import java.util.List;


public interface UserRepositoryCustom {
    List<User> findUsersByRole(Class c);
}

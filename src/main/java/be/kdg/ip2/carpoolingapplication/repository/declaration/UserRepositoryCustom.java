package be.kdg.ip2.carpoolingapplication.repository.declaration;

import be.kdg.ip2.carpoolingapplication.domain.user.User;

import java.util.List;


public interface UserRepositoryCustom {
    List<User> findUsersByRole(Class c);
}

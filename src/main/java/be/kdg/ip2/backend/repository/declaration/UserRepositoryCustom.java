package be.kdg.ip2.backend.repository.declaration;

import be.kdg.ip2.backend.domain.user.User;

import java.util.List;


public interface UserRepositoryCustom {
    List<User> findUsersByRole(Class c);
}

package be.kdg.ip2.backend.repository.declaration;

import be.kdg.ip2.backend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    User findUserByUsername(String username);

    User findUserByEmail(String email);
}

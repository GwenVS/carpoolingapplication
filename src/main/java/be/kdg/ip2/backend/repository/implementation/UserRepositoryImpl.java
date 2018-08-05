package be.kdg.ip2.backend.repository.implementation;

import be.kdg.ip2.backend.domain.user.User;
import be.kdg.ip2.backend.repository.declaration.UserRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    // Inject persistence context for low-level access
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findUsersByRole(Class c) {
        TypedQuery<User> q = em.createQuery("SELECT u from User u where TYPE(u.roles) = " + c.getSimpleName(), User.class);
        return q.getResultList();
    }
}

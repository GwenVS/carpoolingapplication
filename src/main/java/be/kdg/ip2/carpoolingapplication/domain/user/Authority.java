package be.kdg.ip2.carpoolingapplication.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Model class that represents the authority (authentication) of a user
 */

@Entity
@Table
public class Authority implements GrantedAuthority{
    @Id
    @Column(columnDefinition = "serial") //datatype
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Indicates that the persistence provider must assign primary keys for the entity using a database identity column.
    private Long id;

    @Column
    private String name;

    @ManyToOne(targetEntity = User.class,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    //@JsonIgnoreProperties(value = {"userId", "authorities", "cars", "userRideInfos", "rideRequests"})
    @JoinColumn(name="userId")
    private User user;

    public Authority() {
    }

    public Authority(String name, User user) {
        this.name = name;
        this.user = user;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

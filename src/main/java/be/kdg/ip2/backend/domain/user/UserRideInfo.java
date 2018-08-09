package be.kdg.ip2.backend.domain.user;

import be.kdg.ip2.backend.domain.Ride;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;


/**
 * Model class that connects rides with carpoolers
 * When a carpooler is part of a Ride this UserRideInfo will be generated
 */
@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@userRideInfoId")
public class UserRideInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userRideInfoId;

    @Column()
    private boolean isDriver;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties(value = {"authorities", "cars", "userRideInfos", "rideRequests"})
    private User user;

    @ManyToOne(targetEntity = Ride.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "rideId")
    @JsonIgnoreProperties(value = {"subRides", "userRideInfos", "rideRequests", "locations"})
    private Ride ride;


    //constructors
    public UserRideInfo() {
    }

    public UserRideInfo(boolean isDriver, User user, Ride ride) {
        this.isDriver = isDriver;
        this.user = user;
        this.ride = ride;
    }


    //getters and setters
    public Long getUserRideInfoId() {
        return userRideInfoId;
    }

    public void setUserRideInfoId(Long userRideInfoId) {
        this.userRideInfoId = userRideInfoId;
    }

    public boolean isDriver() {
        return isDriver;
    }

    public void setDriver(boolean driver) {
        isDriver = driver;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }
}

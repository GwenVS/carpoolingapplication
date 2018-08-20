package be.kdg.ip2.carpoolingapplication.domain;

import be.kdg.ip2.carpoolingapplication.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table()
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@rideRequestId")
public class RideRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rideRequestId;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties(value = {"userId", "profilePictureFileName", "enabled", "password", "credentialsNonExpired", "accountNonExpired", "accountNonLocked", "authorities", "userRoles", "cars", "userRideInfos", "rideRequests"})
    private User user;

    @ManyToOne(targetEntity = Ride.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "rideId")
    @JsonIgnoreProperties(value = {"subRides", "userRideInfos", "rideRequests", "locations"})
    private Ride ride;

    @Column
    private String Text;


    //constructors
    public RideRequest() {
    }

    public RideRequest(User user, Ride ride, String text) {
        this.user = user;
        this.ride = ride;
        Text = text;
    }


    //getters and setters
    public Long getRideRequestId() {
        return rideRequestId;
    }

    public void setRideRequestId(Long rideRequestId) {
        this.rideRequestId = rideRequestId;
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

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}

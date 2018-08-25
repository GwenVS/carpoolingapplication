package be.kdg.ip2.carpoolingapplication.domain;

import be.kdg.ip2.carpoolingapplication.domain.locations.EndLocation;
import be.kdg.ip2.carpoolingapplication.domain.locations.StartLocation;
import be.kdg.ip2.carpoolingapplication.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table()
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RideRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rideRequestId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "rideRequest")
    private StartLocation startLocation;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "rideRequest")
    private EndLocation endLocation;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties(value = {"userId", "profilePictureFileName", "enabled", "password", "credentialsNonExpired", "accountNonExpired", "accountNonLocked", "authorities", "userRoles", "cars", "userRideInfos", "rideRequests"})
    private User user;

    @ManyToOne(targetEntity = Ride.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "rideId")
    @JsonIgnoreProperties(value = {"subRides", "userRideInfos", "rideRequests", "locations"})
    private Ride ride;

    @Column
    private String requestText;


    //constructors
    public RideRequest() {
    }

    public RideRequest(User user, Ride ride, String requestText) {
        this.user = user;
        this.ride = ride;
        this.requestText = requestText;
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

    public String getRequestText() {
        return requestText;
    }

    public void setRequestText(String requestText) {
        this.requestText = requestText;
    }
}

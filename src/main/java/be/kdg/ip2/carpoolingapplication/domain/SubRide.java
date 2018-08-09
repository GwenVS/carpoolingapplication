package be.kdg.ip2.carpoolingapplication.domain;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;

@Entity
@Table()
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@subRideId")
public class SubRide {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subRideId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "subRide")
    private Location startLocation;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "subRide")
    private Location stopLocation;

    @ManyToOne(targetEntity = Ride.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "rideId")
    @JsonIgnoreProperties(value = {"subRides", "userRideInfos", "rideRequests", "locations"})
    private Ride ride;

    //constructors
    public SubRide() {
    }

    public SubRide(Location startLocation, Location stopLocation, Ride ride) {
        this.startLocation = startLocation;
        this.stopLocation = stopLocation;
        this.ride = ride;
    }

    //getters and setters
    public Long getSubRideId() {
        return subRideId;
    }

    public void setSubRideId(Long subRideId) {
        this.subRideId = subRideId;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public Location getStopLocation() {
        return stopLocation;
    }

    public void setStopLocation(Location stopLocation) {
        this.stopLocation = stopLocation;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }
}
package be.kdg.ip2.carpoolingapplication.domain;

import be.kdg.ip2.carpoolingapplication.domain.locations.EndLocation;
import be.kdg.ip2.carpoolingapplication.domain.locations.StartLocation;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table()
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@subRideId")
public class SubRide {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subRideId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "subRide")
    private StartLocation startLocation;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "subRide")
    private EndLocation endLocation;

    @ManyToOne(targetEntity = Ride.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "rideId")
    @JsonIgnore
    private Ride ride;

    //constructors
    public SubRide() {
    }

    public SubRide(StartLocation startLocation, EndLocation endLocation, Ride ride) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.ride = ride;
    }

    //getters and setters
    public Long getSubRideId() {
        return subRideId;
    }

    public void setSubRideId(Long subRideId) {
        this.subRideId = subRideId;
    }

    public StartLocation getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(StartLocation startLocation) {
        this.startLocation = startLocation;
    }

    public EndLocation getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(EndLocation endLocation) {
        this.endLocation = endLocation;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }
}
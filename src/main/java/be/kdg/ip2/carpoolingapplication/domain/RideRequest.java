package be.kdg.ip2.carpoolingapplication.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table()
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="@rideRequestId")
public class RideRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rideRequestId;

    @ManyToOne(targetEntity = Carpooler.class,fetch = FetchType.EAGER)
    @JoinColumn(name="carpoolerId")
    @JsonIgnore
    private Carpooler carpooler;

    @ManyToOne(targetEntity = Ride.class, fetch = FetchType.EAGER)
    @JoinColumn(name="rideId")
    @JsonIgnore
    private Ride ride;

    @Column
    private String Text;


    //constructors
    public RideRequest() {
    }

    public RideRequest(Carpooler carpooler, Ride ride, String text) {
        this.carpooler = carpooler;
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

    public Carpooler getCarpooler() {
        return carpooler;
    }

    public void setCarpooler(Carpooler carpooler) {
        this.carpooler = carpooler;
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

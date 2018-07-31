package be.kdg.ip2.carpoolingapplication.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 * Model class that connects rides with carpoolers
 * When a carpooler is part of a Ride this CarpoolerRideInfo will be generated
 */
@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property="@carpoolerRideInfoId")
public class CarpoolerRideInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carpoolerRideInfoId;

    @Column()
    private boolean isDriver;

    @ManyToOne(targetEntity = Carpooler.class, fetch = FetchType.EAGER)
    @JoinColumn(name="carpoolerId")
    @JsonIgnore
    private Carpooler carpooler;

    @ManyToOne(targetEntity = Ride.class, fetch = FetchType.EAGER)
    @JoinColumn(name="rideId")
    @JsonIgnore
    private Ride ride;


    //constructors
    public CarpoolerRideInfo() {
    }

    public CarpoolerRideInfo(boolean isDriver, Carpooler carpooler, Ride ride) {
        this.isDriver = isDriver;
        this.carpooler = carpooler;
        this.ride = ride;
    }


    //getters and setters
    public Long getCarpoolerRideInfoId() {
        return carpoolerRideInfoId;
    }

    public void setCarpoolerRideInfoId(Long carpoolerRideInfoId) {
        this.carpoolerRideInfoId = carpoolerRideInfoId;
    }

    public boolean getIsDriver() {
        return isDriver;
    }

    public void setIsDriver(boolean driver) {
        isDriver = driver;
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
}
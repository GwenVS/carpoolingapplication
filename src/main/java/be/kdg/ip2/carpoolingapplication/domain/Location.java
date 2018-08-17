package be.kdg.ip2.carpoolingapplication.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table()
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property="@locationId")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long locationId;

    @Column()
    private double latitude;

    @Column()
    private double longitude;

    @OneToOne(targetEntity = SubRide.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "subRideId")
    @JsonIgnoreProperties(value = {"startLocation", "stopLocation"})
    private SubRide subRide;


    @ManyToOne(targetEntity = Ride.class, fetch = FetchType.EAGER)
    @JoinColumn(name="rideId")
    @JsonIgnoreProperties(value = {"subRides", "userRideInfos", "rideRequests", "locations"})
    private Ride ride;

    //constructors
    public Location() {
    }

    public Location(double latitude, double longitude, SubRide subRide) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.subRide = subRide;
        this.ride = subRide.getRide();
    }

    public Location(double latitude, double longitude, Ride ride) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.ride = ride;
    }

    //getters and setters
    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public SubRide getSubRide() {
        return subRide;
    }

    public void setSubRide(SubRide subRide) {
        this.subRide = subRide;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }
}
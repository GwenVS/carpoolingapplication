package be.kdg.ip2.carpoolingapplication.domain;

import be.kdg.ip2.carpoolingapplication.domain.user.User;
import be.kdg.ip2.carpoolingapplication.domain.user.UserRideInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table()
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@rideId")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rideId;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime departureTimeOutwardJourney;

    @Column()
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime departureTimeReturnTrip;

    @Column(nullable = false)
    private RideType rideType;

    @Column()
    private String CreatorDriverUsername;

    @Column()
    private Long carId;

    @Column
    @OneToMany(mappedBy = "ride", targetEntity = SubRide.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private List<SubRide> subRides = new ArrayList<>();

    @Column
    @OneToMany(mappedBy = "ride", targetEntity = UserRideInfo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private List<UserRideInfo> userRideInfos = new ArrayList<>();

    @Column
    @OneToMany(mappedBy = "ride", targetEntity = RideRequest.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private List<RideRequest> rideRequests = new ArrayList<>();

    @Column
    @OneToMany(mappedBy = "ride", targetEntity = Location.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private List<Location> locations = new ArrayList<>();

    //constructors
    public Ride() {
    }

    //ride with returntrip
    public Ride(LocalDateTime departureTimeOutwardJourney, LocalDateTime departureTimeReturnTrip, List<UserRideInfo> userRideInfos, List<RideRequest> rideRequests, List<Location> locations) {
        this.departureTimeOutwardJourney = departureTimeOutwardJourney;
        this.departureTimeReturnTrip = departureTimeReturnTrip;
        this.rideType = RideType.BackAndForth;
        this.userRideInfos = userRideInfos;
        this.rideRequests = rideRequests;
        this.locations = locations;
    }

    public Ride(LocalDateTime departureTimeOutwardJourney, LocalDateTime departureTimeReturnTrip) {
        this.departureTimeOutwardJourney = departureTimeOutwardJourney;
        this.departureTimeReturnTrip = departureTimeReturnTrip;
        this.rideType = RideType.BackAndForth;
    }

    //ride without return trip
    public Ride(LocalDateTime departureTimeOutwardJourney, List<Location> locations, List<UserRideInfo> userRideInfos, List<RideRequest> rideRequests) {
        this.departureTimeOutwardJourney = departureTimeOutwardJourney;
        this.rideType = RideType.Single;
        this.userRideInfos = userRideInfos;
        this.rideRequests = rideRequests;
        this.locations = locations;
    }

    public Ride(LocalDateTime departureTimeOutwardJourney) {
        this.departureTimeOutwardJourney = departureTimeOutwardJourney;
        this.rideType = RideType.Single;
    }



    //getters and setters
    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public LocalDateTime getDepartureTimeOutwardJourney() {
        return departureTimeOutwardJourney;
    }

    public void setDepartureTimeOutwardJourney(LocalDateTime departureTimeOutwardJourney) {
        this.departureTimeOutwardJourney = departureTimeOutwardJourney;
    }

    public LocalDateTime getDepartureTimeReturnTrip() {
        return departureTimeReturnTrip;
    }

    public void setDepartureTimeReturnTrip(LocalDateTime departureTimeReturnTrip) {
        this.departureTimeReturnTrip = departureTimeReturnTrip;
    }

    public RideType getRideType() {
        return rideType;
    }

    public void setRideType(RideType rideType) {
        this.rideType = rideType;
    }

    public String getCreatorDriverUsername() {
        return CreatorDriverUsername;
    }

    public void setCreatorDriverUsername(String creatorDriverUsername) {
        CreatorDriverUsername = creatorDriverUsername;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public List<SubRide> getSubRides() {
        return subRides;
    }

    public void setSubRides(List<SubRide> subRides) {
        this.subRides = subRides;
    }

    public void addSubRide(SubRide subRide) {
        this.subRides.add(subRide);
    }

    public List<UserRideInfo> getUserRideInfos() {
        return userRideInfos;
    }

    public void setUserRideInfos(List<UserRideInfo> userRideInfos) {
        this.userRideInfos = userRideInfos;
    }

    public void addUserRideInfo(UserRideInfo userRideInfo) {
        this.userRideInfos.add(userRideInfo);
    }

    public List<RideRequest> getRideRequests() {
        return rideRequests;
    }

    public void setRideRequests(List<RideRequest> rideRequests) {
        this.rideRequests = rideRequests;
    }

    public void addRideRequest(RideRequest rideRequest) {
        this.rideRequests.add(rideRequest);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void addLocation(Location location) {
        this.locations.add(location);
    }

    @Override
    public String toString() {
        return "Ride{" +
                "rideId=" + rideId +
                ", departureTimeOutwardJourney=" + departureTimeOutwardJourney +
                ", departureTimeReturnTrip=" + departureTimeReturnTrip +
                ", rideType=" + rideType +
                '}';
    }
}

package be.kdg.ip2.carpoolingapplication.domain;
import be.kdg.ip2.carpoolingapplication.domain.enums.RideType;
import be.kdg.ip2.carpoolingapplication.domain.locations.RideLocation;
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
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rideId;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime departureTimeOutwardJourney;

    @Column()
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime departureTimeReturnTrip;

    @Column(nullable = false)
    private RideType rideType;

    @ManyToOne(targetEntity = Car.class,fetch = FetchType.EAGER)
    @JoinColumn(name="carId")
    @JsonIgnoreProperties(value = {"rides", "user"})
    private Car chosenCar;

    @Column
    @OneToMany(mappedBy = "ride", targetEntity = UserRideInfo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private List<UserRideInfo> userRideInfos = new ArrayList<>();

    @Column
    @OneToMany(mappedBy = "ride", targetEntity = RideRequest.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JsonIgnoreProperties("ride")
    private List<RideRequest> rideRequests = new ArrayList<>();

    @Column
    @OneToMany(mappedBy = "ride", targetEntity = RideLocation.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private List<RideLocation> locations = new ArrayList<>();

    //constructors
    public Ride() {
    }

    //ride with returntrip
    public Ride(LocalDateTime departureTimeOutwardJourney, LocalDateTime departureTimeReturnTrip, List<UserRideInfo> userRideInfos, List<RideRequest> rideRequests, List<RideLocation> locations, Car chosenCar) {
        this.departureTimeOutwardJourney = departureTimeOutwardJourney;
        this.departureTimeReturnTrip = departureTimeReturnTrip;
        this.rideType = RideType.BackAndForth;
        this.userRideInfos = userRideInfos;
        this.rideRequests = rideRequests;
        this.locations = locations;
        this.chosenCar = chosenCar;
    }

    public Ride(LocalDateTime departureTimeOutwardJourney, LocalDateTime departureTimeReturnTrip, Car chosenCar) {
        this.departureTimeOutwardJourney = departureTimeOutwardJourney;
        this.departureTimeReturnTrip = departureTimeReturnTrip;
        this.rideType = RideType.BackAndForth;
        this.chosenCar = chosenCar;
    }

    //ride without return trip
    public Ride(LocalDateTime departureTimeOutwardJourney, List<RideLocation> locations, List<UserRideInfo> userRideInfos, List<RideRequest> rideRequests, Car chosenCar) {
        this.departureTimeOutwardJourney = departureTimeOutwardJourney;
        this.rideType = RideType.Single;
        this.userRideInfos = userRideInfos;
        this.rideRequests = rideRequests;
        this.locations = locations;
        this.chosenCar = chosenCar;
    }

    public Ride(LocalDateTime departureTimeOutwardJourney, Car chosenCar) {
        this.departureTimeOutwardJourney = departureTimeOutwardJourney;
        this.rideType = RideType.Single;
        this.chosenCar = chosenCar;
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

    public Car getChosenCar() {
        return chosenCar;
    }

    public void setChosenCar(Car chosenCar) {
        this.chosenCar = chosenCar;
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

    public List<RideLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<RideLocation> locations) {
        this.locations = locations;
    }

    public void addLocation(RideLocation location) {
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

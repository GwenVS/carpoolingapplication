package be.kdg.ip2.carpoolingapplication.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table()
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="@rideId")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rideId;

    @Column(nullable = false)
    private Location departureLocation;

    @Column(nullable = false)
    private Location arrivalLocation;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd@HH:mm:ss")
    private Date departureTimeOutwardJourney;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd@HH:mm:ss")
    private Date departureTimeReturnTrip;

    @Column(nullable = false)
    private int AmountOfPassengers;

    @Column(nullable = false)
    private RideType rideType;

    @Column
    @OneToMany(mappedBy = "ride",targetEntity = Location.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private List<Location> passagepoints = new ArrayList<>();

    @Column
    @OneToMany(mappedBy = "ride",targetEntity = CarpoolerRideInfo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private List<CarpoolerRideInfo> carpoolerRideInfos = new ArrayList<>();

    @Column
    @OneToMany(mappedBy = "ride",targetEntity = RideRequest.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private List<RideRequest> rideRequests;


    //constructors
    public Ride() {
    }

    public Ride(Location departureLocation, Location arrivalLocation, Date departureTimeOutwardJourney, Date departureTimeReturnTrip, int amountOfPassengers, RideType rideType, List<Location> passagepoints, List<CarpoolerRideInfo> carpoolerRideInfos, List<RideRequest> rideRequests) {
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureTimeOutwardJourney = departureTimeOutwardJourney;
        this.departureTimeReturnTrip = departureTimeReturnTrip;
        AmountOfPassengers = amountOfPassengers;
        this.rideType = rideType;
        this.passagepoints = passagepoints;
        this.carpoolerRideInfos = carpoolerRideInfos;
        this.rideRequests = rideRequests;
    }


    //getters and setters
    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public Location getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(Location departureLocation) {
        this.departureLocation = departureLocation;
    }

    public Location getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(Location arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public Date getDepartureTimeOutwardJourney() {
        return departureTimeOutwardJourney;
    }

    public void setDepartureTimeOutwardJourney(Date departureTimeOutwardJourney) {
        this.departureTimeOutwardJourney = departureTimeOutwardJourney;
    }

    public Date getDepartureTimeReturnTrip() {
        return departureTimeReturnTrip;
    }

    public void setDepartureTimeReturnTrip(Date departureTimeReturnTrip) {
        this.departureTimeReturnTrip = departureTimeReturnTrip;
    }

    public int getAmountOfPassengers() {
        return AmountOfPassengers;
    }

    public void setAmountOfPassengers(int amountOfPassengers) {
        AmountOfPassengers = amountOfPassengers;
    }

    public RideType getRideType() {
        return rideType;
    }

    public void setRideType(RideType rideType) {
        this.rideType = rideType;
    }

    public List<Location> getPassagepoints() {
        return passagepoints;
    }

    public void setPassagepoints(List<Location> passagepoints) {
        this.passagepoints = passagepoints;
    }

    public void addPassagePoint(Location location) { this.passagepoints.add(location); }

    public List<CarpoolerRideInfo> getCarpoolerRideInfos() {
        return carpoolerRideInfos;
    }

    public void setCarpoolerRideInfos(List<CarpoolerRideInfo> carpoolerRideInfos) {
        this.carpoolerRideInfos = carpoolerRideInfos;
    }

    public void addCarpoolerRideInfo(CarpoolerRideInfo carpoolerRideInfo) { this.carpoolerRideInfos.add(carpoolerRideInfo); }

    public List<RideRequest> getRideRequests() {
        return rideRequests;
    }

    public void setRideRequests(List<RideRequest> rideRequests) {
        this.rideRequests = rideRequests;
    }

    public void addRideRequest(RideRequest rideRequest) { this.rideRequests.add(rideRequest); }
}

package be.kdg.ip2.carpoolingapplication.dto;
import be.kdg.ip2.carpoolingapplication.domain.enums.RideType;

import java.util.List;

public class GetRideDto {
    private String departureTimeOutwardJourney;
    private String departureTimeReturnTrip;
    private RideType rideType;
    private String CreatorDriverUsername;
    private int carId;
    private List<SubRideDto> subRides;
    private List<UserRideInfoDto> userRideInfos;
    private List<RideRequestDto> rideRequests;

    public String getDepartureTimeOutwardJourney() {
        return departureTimeOutwardJourney;
    }

    public void setDepartureTimeOutwardJourney(String departureTimeOutwardJourney) {
        this.departureTimeOutwardJourney = departureTimeOutwardJourney;
    }

    public String getDepartureTimeReturnTrip() {
        return departureTimeReturnTrip;
    }

    public void setDepartureTimeReturnTrip(String departureTimeReturnTrip) {
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

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public List<SubRideDto> getSubRides() {
        return subRides;
    }

    public void setSubRides(List<SubRideDto> subRides) {
        this.subRides = subRides;
    }

    public List<UserRideInfoDto> getUserRideInfos() {
        return userRideInfos;
    }

    public void setUserRideInfos(List<UserRideInfoDto> userRideInfos) {
        this.userRideInfos = userRideInfos;
    }

    public List<RideRequestDto> getRideRequests() {
        return rideRequests;
    }

    public void setRideRequests(List<RideRequestDto> rideRequests) {
        this.rideRequests = rideRequests;
    }
}

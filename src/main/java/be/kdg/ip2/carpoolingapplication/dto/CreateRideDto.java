package be.kdg.ip2.carpoolingapplication.dto;
import be.kdg.ip2.carpoolingapplication.domain.RideType;

import java.util.List;

public class CreateRideDto {
    private String departureTimeOutwardJourney;
    private String departureTimeReturnTrip;
    private List<LocationDto> locations;
    private int carId;
    private RideType rideType;

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

    public List<LocationDto> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationDto> locations) {
        this.locations = locations;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public RideType getRideType() {
        return rideType;
    }

    public void setRideType(RideType rideType) {
        this.rideType = rideType;
    }
}

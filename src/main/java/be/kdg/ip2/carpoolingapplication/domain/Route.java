package be.kdg.ip2.carpoolingapplication.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Route {
    private Location departure;
    private Location arrival;
    private List<Location> passagepoints = new ArrayList<>();
    private Car car;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd@HH:mm:ss")
    private Date departureTimeOutwardJourney;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd@HH:mm:ss")
    private Date departureTimeReturnTrip;
    private int AmountOfPassengers;
    private RouteType routeType;


    //getters and setters
    public Location getDeparture() {
        return departure;
    }

    public void setDeparture(Location departure) {
        this.departure = departure;
    }

    public Location getArrival() {
        return arrival;
    }

    public void setArrival(Location arrival) {
        this.arrival = arrival;
    }

    public List<Location> getPassagepoints() {
        return passagepoints;
    }

    public void setPassagepoints(List<Location> passagepoints) {
        this.passagepoints = passagepoints;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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

    public RouteType getRouteType() {
        return routeType;
    }

    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }
}

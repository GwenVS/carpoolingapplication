package be.kdg.ip2.carpoolingapplication.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Route {
    private Location departure;
    private Location arrival;
    private List<Location> passagepoints = new ArrayList<>();
    private Car car;
    private LocalDateTime departureTimeOutwardJourney;
    private LocalDateTime departureTimeReturnTrip;
    private int AmountOfPassengers;
}

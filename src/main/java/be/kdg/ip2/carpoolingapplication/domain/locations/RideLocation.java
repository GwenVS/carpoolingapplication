package be.kdg.ip2.carpoolingapplication.domain.locations;

import be.kdg.ip2.carpoolingapplication.domain.Ride;
import be.kdg.ip2.carpoolingapplication.domain.locations.Location;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class RideLocation extends Location {

    @ManyToOne(targetEntity = Ride.class, fetch = FetchType.EAGER)
    @JoinColumn(name="rideId")
    @JsonIgnore
    private Ride ride;

    public RideLocation() {
    }

    public RideLocation(double latitude, double longitude, Ride ride) {
        super(latitude, longitude);
        this.ride = ride;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }
}

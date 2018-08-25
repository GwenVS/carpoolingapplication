package be.kdg.ip2.carpoolingapplication.domain.locations;

import be.kdg.ip2.carpoolingapplication.domain.RideRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class EndLocation extends Location {
    @OneToOne(targetEntity = RideRequest.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "rideRequestId")
    @JsonIgnore
    private RideRequest rideRequest;

    public RideRequest getRideRequest() {
        return rideRequest;
    }

    public void setRideRequest(RideRequest rideRequest) {
        this.rideRequest = rideRequest;
    }
}

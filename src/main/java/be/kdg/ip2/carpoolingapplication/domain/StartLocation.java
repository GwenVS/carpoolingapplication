package be.kdg.ip2.carpoolingapplication.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table()
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property="@locationId")
public class StartLocation extends Location{
    @OneToOne(targetEntity = SubRide.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "subRideId")
    @JsonIgnore
    private SubRide subRide;


    public StartLocation() {
    }

    public StartLocation(double latitude, double longitude, SubRide subRide) {
        super(latitude, longitude);
        this.subRide = subRide;
    }

    public StartLocation(Location location, SubRide subRide){
        super(location.getLatitude(), location.getLongitude());
        this.subRide = subRide;
    }

    public SubRide getSubRide() {
        return subRide;
    }

    public void setSubRide(SubRide subRide) {
        this.subRide = subRide;
    }
}

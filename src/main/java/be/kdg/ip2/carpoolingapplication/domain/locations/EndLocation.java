package be.kdg.ip2.carpoolingapplication.domain.locations;

import be.kdg.ip2.carpoolingapplication.domain.SubRide;
import be.kdg.ip2.carpoolingapplication.domain.locations.Location;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table()
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property="@locationId")
public class EndLocation extends Location {
    @OneToOne(targetEntity = SubRide.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "subRideId")
    @JsonIgnore
    private SubRide subRide;

    public EndLocation() {
    }

    public EndLocation(Location location, SubRide subRide){
        super(location.getLatitude(), location.getLongitude());
        this.subRide = subRide;
    }

    public EndLocation(double latitude, double longitude, SubRide subRide) {
        super(latitude, longitude);
        this.subRide = subRide;
    }

    public SubRide getSubRide() {
        return subRide;
    }

    public void setSubRide(SubRide subRide) {
        this.subRide = subRide;
    }
}
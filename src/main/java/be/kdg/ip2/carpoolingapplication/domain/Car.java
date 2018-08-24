package be.kdg.ip2.carpoolingapplication.domain;
import be.kdg.ip2.carpoolingapplication.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table()
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@JsonIdentityInfo(generator= ObjectIdGenerators.UUIDGenerator.class, property="@carId")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @Column(length = 50)
    private String type;

    @Column(nullable = false)
    private double consumption;

    @Column(nullable = false)
    private int maxAmountPassengers;

    @ManyToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(name="userId")
    @JsonIgnoreProperties(value = {"userId", "profilePictureFileName","enabled","password","credentialsNonExpired", "accountNonExpired", "accountNonLocked","userRoles","authorities", "cars", "userRideInfos", "rideRequests"})
    private User user;

    @Column
    @OneToMany(mappedBy = "chosenCar", targetEntity = Ride.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private List<Ride> rides = new ArrayList<>();


    //constructors
    public Car() {
    }

    public Car(String type, double consumption, int maxAmountPassengers, User user) {
        this.type = type;
        this.consumption = consumption;
        this.maxAmountPassengers = maxAmountPassengers;
        this.user = user;
    }


    //getters and setters
    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public int getMaxAmountPassengers() {
        return maxAmountPassengers;
    }

    public void setMaxAmountPassengers(int maxAmountPassengers) {
        this.maxAmountPassengers = maxAmountPassengers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public void addRide(Ride ride) { this.rides.add(ride); }
}

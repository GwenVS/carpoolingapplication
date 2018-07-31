package be.kdg.ip2.carpoolingapplication.domain;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table()
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="@carId")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carId;

    @Column(length = 50)
    private String type;

    @Column(nullable = false)
    private double consumption;

    @Column(nullable = false)
    private int maxAmountPassengers;

    @ManyToOne(targetEntity = Carpooler.class,fetch = FetchType.EAGER)
    @JoinColumn(name="carpoolerId")
    @JsonIgnoreProperties(value = {"cars", "carpoolerRideInfos", "rideRequests"})
    private Carpooler carpooler;


    //constructors
    public Car() {
    }

    public Car(String type, double consumption, int maxAmountPassengers, Carpooler carpooler) {
        this.type = type;
        this.consumption = consumption;
        this.maxAmountPassengers = maxAmountPassengers;
        this.carpooler = carpooler;
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

    public Carpooler getCarpooler() {
        return carpooler;
    }

    public void setCarpooler(Carpooler carpooler) {
        this.carpooler = carpooler;
    }
}

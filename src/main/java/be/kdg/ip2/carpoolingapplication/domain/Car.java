package be.kdg.ip2.carpoolingapplication.domain;
import javax.persistence.*;

@Entity
@Table()
public class Car {
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
    private Carpooler carpooler;


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

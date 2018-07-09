package be.kdg.ip2.carpoolingapplication.domain;
import javax.persistence.*;

@Entity
@Table()
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "car_id")
    private int carId;

    @Column(length = 50)
    private String type;

    @Column(nullable = false)
    private double consumption;

    @Column(nullable = false)
    private int maxAmountPassengers;

    @ManyToOne(targetEntity = Carpooler.class,fetch = FetchType.EAGER)
    @JoinColumn(name="carpooler_id")
    private Carpooler carpooler;
}

package be.kdg.ip2.carpoolingapplication.domain;
import org.hibernate.annotations.Fetch;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table()
public class Carpooler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "carpooler_id")
    private long carpoolerId;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(length = 50, nullable = false)
    private String username;//todo:emailadres: checken op @?

    @Column(nullable = false)
    private String encryptedPassword;

    @Column()
    private boolean smoker;

    @Column(nullable = false)
    private Gender gender;


    @Column
    @OneToMany(mappedBy = "carpooler",targetEntity = Car.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private List<Car> cars = new ArrayList<>();
}

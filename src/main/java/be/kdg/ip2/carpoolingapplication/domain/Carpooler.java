package be.kdg.ip2.carpoolingapplication.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table()
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Carpooler {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long carpoolerId;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(length = 50, nullable = false)
    private String username;//todo:emailadres: checken op @?

    @Column()
    private boolean isSmoker;

    @Column(nullable = false)
    private Gender gender;

    @Column
    @OneToMany(mappedBy = "carpooler",targetEntity = Car.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private List<Car> cars = new ArrayList<>();


    //getters and setters
    public long getCarpoolerId() {
        return carpoolerId;
    }

    public void setCarpoolerId(long carpoolerId) {
        this.carpoolerId = carpoolerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getIsSmoker() {
        return isSmoker;
    }

    public void setIsSmoker(boolean isSmoker) {
        this.isSmoker = isSmoker;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}

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
    private String userName;//todo:emailadres: checken op @?

    //@Column(nullable = false)
    //private String encryptedPassword;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /*public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }*/

    public boolean isSmoker() {
        return isSmoker;
    }

    public void setSmoker(boolean smoker) {
        this.isSmoker = smoker;
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

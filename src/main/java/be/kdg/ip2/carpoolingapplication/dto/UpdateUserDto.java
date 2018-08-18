package be.kdg.ip2.carpoolingapplication.dto;
import be.kdg.ip2.carpoolingapplication.domain.enums.Gender;
import java.time.LocalDate;

public class UpdateUserDto {
    //Username can not be changed and is only used for checking if the user is trying to change the same user object
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthday;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}

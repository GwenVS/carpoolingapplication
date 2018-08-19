package be.kdg.ip2.carpoolingapplication.dto;

public class UserRideInfoDto {
    private boolean isDriver;
    private Long rideId;
    private Long userId;

    public boolean getIsDriver() {
        return isDriver;
    }

    public void setIsDriver(boolean driver) {
        isDriver = driver;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

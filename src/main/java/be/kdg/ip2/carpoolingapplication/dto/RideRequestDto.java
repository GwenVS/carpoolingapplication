package be.kdg.ip2.carpoolingapplication.dto;

public class RideRequestDto {
    private String Text;
    private Long rideId;
    private Long userId;


    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
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

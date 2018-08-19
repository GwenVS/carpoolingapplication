package be.kdg.ip2.carpoolingapplication.dto;


public class SubRideDto {
    private LocationDto startLocation;
    private LocationDto endLocation;

    public LocationDto getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(LocationDto startLocation) {
        this.startLocation = startLocation;
    }

    public LocationDto getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(LocationDto endLocation) {
        this.endLocation = endLocation;
    }
}

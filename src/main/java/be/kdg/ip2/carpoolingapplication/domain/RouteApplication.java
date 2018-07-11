package be.kdg.ip2.carpoolingapplication.domain;

public class RouteApplication {
    private Location pickupLocation;
    private Location dropoffLocation;
    private String Text;


    //getters and setters
    public Location getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Location getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(Location dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}

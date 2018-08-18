package be.kdg.ip2.carpoolingapplication.dto;

public class CarDto {
    private String type;
    private double consumption;
    private int maxAmountPassengers;

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
}

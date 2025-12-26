package parkinglotdesign.src.domain;

import java.util.UUID;

public class pricingRule {
    private UUID id;
    private Vehicle.VehicleType type;
    private double hourrate;
    private double flatrate;

    public void princingRule(Vehicle.VehicleType type, double hourrate, double flatrate){
        this.id = UUID.randomUUID();
        this.type = type;
        this.hourrate = hourrate;
        this.flatrate = flatrate;
    }

    public void updateRates(double hourrate, double flatrate){
        this.hourrate = hourrate;
        this.flatrate = flatrate;
    }

    public void updateHourRate(double hourrate){
        this.hourrate = hourrate;
    }

    public void updateFlatRate(double flatrate){
        this.flatrate = flatrate;
    }

    public UUID getId(){
        return id;
    }

    public Vehicle.VehicleType getVehicleType(){
        return type;
    }

    public double getHourrate(){
        return hourrate;
    }

    public double getFlatrate(){
        return flatrate;
    }

    @Override
    public String toString(){
        return "PricingRule{" +
                "id=" + id +
                ", vehicleType=" + type +
                ", ratePerHour=" + hourrate +
                ", flatRate=" + flatrate +
                '}';
    }
}

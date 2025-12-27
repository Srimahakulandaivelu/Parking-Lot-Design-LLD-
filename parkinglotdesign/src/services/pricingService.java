package parkinglotdesign.src.services;

import parkinglotdesign.src.domain.Vehicle;
import parkinglotdesign.src.repositories.pricingRuleRepository;
import parkinglotdesign.src.domain.Ticket;
import parkinglotdesign.src.domain.pricingRule;

import java.util.Optional;
import java.time.Duration;
import java.time.LocalDateTime;

public class pricingService {

    private pricingRuleRepository pricingRepo;

    public pricingService(pricingRuleRepository pricingRepo){
        this.pricingRepo = pricingRepo;
    }

    public double calculateFee(Ticket ticket){
        System.out.println("[SERVICE] Calculating fee for ticket: " + ticket.getId());

        Vehicle.VehicleType type = Vehicle.VehicleType.CAR;

        Optional<pricingRule> rule = pricingRepo.findByVehicleType(type);

        if(rule.isEmpty()){
            throw new IllegalStateException("No pricing rule found for vehicle type: " + type);
        }

        pricingRule pricingRule = rule.get();

        double flatRate = pricingRule.getFlatrate();
        double hourlyFee = calculateFee(ticket);

        double finalFee = Math.min(flatRate, hourlyFee);

        System.out.println("[SERVICE] Flat fee: " + flatRate + ", Hourly fee: " + hourlyFee + ", Final fee: " + finalFee + " for vehicle type: " + type);

        return finalFee;
    }

    public double calculateFee(Ticket ticket , double hourRate){
        Duration duration= Duration.between(ticket.getEntryTime(), LocalDateTime.now());

        long hours = duration.toHours();

        if(hours < 1){
            hours = 1;
        }

        return hours * hourRate;
    }

    public void addPricingRule(pricingRule rule) {
        pricingRepo.save(rule);
    }

    public void updatePricingRule(pricingRule rule) {
        pricingRepo.update(rule);
    }
}

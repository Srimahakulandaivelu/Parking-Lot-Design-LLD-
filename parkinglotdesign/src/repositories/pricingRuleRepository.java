package parkinglotdesign.src.repositories;

import parkinglotdesign.src.domain.pricingRule;
import parkinglotdesign.src.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


public class pricingRuleRepository {
    private Map<UUID, pricingRule> rules = new ConcurrentHashMap<>();
    private Map<Vehicle.VehicleType, UUID> vehicleTypeToRule = new ConcurrentHashMap<>();

    public pricingRule save(pricingRule rule) {
        rules.put(rule.getId(), rule);
        vehicleTypeToRule.put(rule.getVehicleType(), rule.getId());
        return rule;
    }

    public Optional<pricingRule> findById(UUID ruleId) {
        return Optional.ofNullable(rules.get(ruleId));
    }

    public Optional<pricingRule> findByVehicleType(Vehicle.VehicleType vehicleType) {
        UUID ruleId = vehicleTypeToRule.get(vehicleType);
        return ruleId != null ? Optional.ofNullable(rules.get(ruleId)) : Optional.empty();
    }

    public List<pricingRule> findAll() {
        return new ArrayList<>(rules.values());
    }

    public void update(pricingRule rule) {
        if (rules.containsKey(rule.getId())) {
            rules.put(rule.getId(), rule);
            vehicleTypeToRule.put(rule.getVehicleType(), rule.getId());
        }
    }

    public void delete(UUID ruleId) {
        pricingRule rule = rules.remove(ruleId);
        if (rule != null) {
            vehicleTypeToRule.remove(rule.getVehicleType());
        }
    }

    public void clear() {
        rules.clear();
        vehicleTypeToRule.clear();
    }
}

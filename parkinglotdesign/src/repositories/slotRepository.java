package parkinglotdesign.src.repositories;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.UUID;
import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import parkinglotdesign.src.domain.ParkingSlot;
import parkinglotdesign.src.domain.Vehicle;

public class slotRepository {

    //initialising data storage
    private Map<UUID, ParkingSlot> slots = new ConcurrentHashMap<>();

    //method to save a new slot
    public ParkingSlot save (ParkingSlot slot){
        slots.put(slot.getId(), slot);
        return slot;
    }

    //to find the slot using its ID, returns optional object
    public Optional<ParkingSlot> findById(UUID slotId){
        return Optional.ofNullable(slots.get(slotId));
    }

    //returns list of available parking slots for the vehicle type
    public List<ParkingSlot> findAvailableSlots(Vehicle.VehicleType type){
       List<ParkingSlot> result = new ArrayList<>();

       for(ParkingSlot slot : slots.values()){
           if(slot.getSlotType() == type && slot.isOccupied() == false){
               result.add(slot);
           }
       }
       return result;
    }

    //returns allocated slot if available
    public Optional<ParkingSlot> allocateSlot(Vehicle.VehicleType type){
        for(ParkingSlot slot : slots.values()){
            if(slot.getSlotType() == type && slot.isOccupied() == false){
                slot.setOccupied(true);
                return Optional.of(slot);
            }
        }

        return Optional.empty();
    }

    //finds the slot by using its slotId and releases it
    public void releaseSlot(UUID slotId){
       ParkingSlot slot = slots.get(slotId);
       if(slot != null){
           slot.setOccupied(false);
       }
    }

    public Map<Vehicle.VehicleType, Long> getStats(){
        Map<Vehicle.VehicleType, Long> stats = new HashMap<>();
        
        for(ParkingSlot slot : slots.values()){
            Vehicle.VehicleType type = slot.getSlotType();
            stats.put(type, stats.getOrDefault(type, 0L) +1);
        }
        
        return stats;
    }

    public void clear(){
        slots.clear();
    }

 }

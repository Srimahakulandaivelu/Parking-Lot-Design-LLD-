package parkinglotdesign.src.services;

import parkinglotdesign.src.domain.ParkingSlot;
import parkinglotdesign.src.repositories.slotRepository;
import parkinglotdesign.src.domain.Vehicle;

import java.util.Optional;
import java.util.UUID;

public class slotService {
    private slotRepository slotRepo;

    public slotService(slotRepository slotRepo){
        this.slotRepo = slotRepo;
    }


    public Optional<ParkingSlot> allocateSlot(Vehicle.VehicleType type){
        System.out.println("[SERVICE] Allocating slot for vehicle type: " + type);

        Optional<ParkingSlot>  slot = slotRepo.allocateSlot(type);

        if(slot.isPresent()){
            System.out.println("[SERVICE] Slot allocated successfully: " + slot.get().getId());
        }

        else {
            System.out.println("[SERVICE] No available slots for vehicle type: " + type);
        }

        return slot;
    }

    public void releaseSlot(UUID slotId){
        System.out.println("[SERVICE] Releasing slot: " + slotId);

        slotRepo.releaseSlot(slotId);

        System.out.println("[SERVICE] Slot released successfully: " + slotId);
    }

    public ParkingSlot createSlot(Vehicle.VehicleType type, int floorNumber){
        ParkingSlot slot = new ParkingSlot(type, floorNumber);

        slotRepo.save(slot);
        return slot;
    }

    public long getAvailableSlots(Vehicle.VehicleType type){
        return slotRepo.findAvailableSlots(type).size();
    }
}

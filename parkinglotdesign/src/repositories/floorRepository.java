package parkinglotdesign.src.repositories;

import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;
import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;


import parkinglotdesign.src.domain.Floor;

public class floorRepository {
    private Map<UUID, Floor> floors = new ConcurrentHashMap<>();
    private Map<Integer, UUID> floorNumberToId = new ConcurrentHashMap<>();

    public Floor save(Floor floor){
        floors.put(floor.getId(), floor);
        floorNumberToId.put(floor.getFloorNumber(), floor.getId());

        return floor;
    }

    public Optional<Floor> findById(UUID floorId){
        return Optional.ofNullable(floors.get(floorId));
    }

    public Optional<Floor> findByNumber(int floorNumber){
        UUID floorId = floorNumberToId.get(floorNumber);

        if(floorId == null){
            return Optional.empty();
        }

        Floor floor = floors.get(floorId);
        return Optional.ofNullable(floor);
    }

    public List<Floor> findAll(){
        return new ArrayList<>(floors.values());
    }

    public boolean existsByNumber(int floorNumber){
        return floorNumberToId.containsKey(floorNumber);
    }

    public void delete(UUID floorId){
        Floor floor = floors.remove(floorId);

        if(floor != null){
            floorNumberToId.remove(floor.getFloorNumber());
        }
    }

    public void clear(){
        floors.clear();
        floorNumberToId.clear();
    }
}


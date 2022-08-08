package domain;

import domain.elevator.Elevator;
import services.Builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Building {

    private final Integer floorCount;
    private final Integer elevatorCount;
    private static Building build = null;
    public List<Floor> floorList;
    public List<Elevator> elevatorList;
    public Map<Integer,Floor> floorMap;

    private Building(Integer floorCount,  Integer elevatorCount){
        this.floorCount = floorCount;
        this.elevatorCount = elevatorCount;
        elevatorList = new ArrayList<>(this.elevatorCount);
        floorList = new ArrayList<>(this.floorCount);
        floorMap = new HashMap<>();
    }
    public static Building getReadyBuilding(){
        return build;
    }

    public static void generateBuilding(Integer floorCount, Integer elevatorCount){
        if (build==null){
            build = new Building(floorCount, elevatorCount);
        }
    }

    public Integer getElevatorCount() {
        return elevatorCount;
    }
    public Integer getLevelCount() {
        return floorCount;
    }

}

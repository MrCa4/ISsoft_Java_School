package services;

import domain.Building;
import domain.Floor;
import domain.elevator.Elevator;
import lombok.extern.slf4j.Slf4j;


import static domain.elevator.ElevatorStates.WAITING;

@Slf4j()
public class Builder {

    public Builder(){

    }

    public void build(Integer floorCount, Integer elevatorCount,Integer elevatorMaxCapacity){
        //Generate Building, Floors, Queues, Elevators

        Building.generateBuilding(floorCount,elevatorCount);
        log.info("Generate building");
        generateFloors(Building.getReadyBuilding());
        log.info("Generate floors");
        generateElevators(Building.getReadyBuilding(),elevatorMaxCapacity);
        log.info("Generate elevators");
    }

    private void generateFloors(Building building){

            for (int i = 0; i < building.getLevelCount(); i++){
                Floor floor = new Floor(i + 1);
                building.floorList.add(floor);
                building.floorMap.put(i + 1, floor);
            }
    }

    private void generateElevators(Building building, Integer maxCapacity){
        for (int i = 0; i < building.getElevatorCount(); i++){
            building.elevatorList.add(new Elevator(maxCapacity));
        }
        building.elevatorList.forEach(el -> el.setElevatorState(WAITING));
    }
}

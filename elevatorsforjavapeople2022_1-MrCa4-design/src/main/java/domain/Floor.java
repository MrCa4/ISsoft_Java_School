package domain;

import domain.elevator.Elevator;

import java.util.*;

public class Floor {


    private final Integer floorNumber;
    private final Queue<Human> downQueue = new ArrayDeque<>();
    private final Queue<Human> upQueue = new ArrayDeque<>();
    public  Queue<Human> getUpQueue() {
        return upQueue;
    }

    public  Queue<Human> getDownQueue() {
        return downQueue;
    }

    public synchronized Integer getFloorNumber() {
        return floorNumber;
    }

    public Floor(Integer floorNumber){
        this.floorNumber= floorNumber;
    }

}

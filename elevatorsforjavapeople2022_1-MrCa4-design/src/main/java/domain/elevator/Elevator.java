package domain.elevator;

import domain.Floor;
import domain.Human;

import lombok.SneakyThrows;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static domain.Building.getReadyBuilding;
import static domain.elevator.ElevatorStates.BUSY;
import static domain.elevator.ElevatorStates.WAITING;

@Slf4j
public class Elevator implements Runnable{

    @Setter
    @Getter
    private Integer maxCapacity = 480;
    @Setter
    @Getter
    private Integer elevatorZoneNumber;
    private Integer currentFloor;
    @Getter
    private Integer defaultFloor;
    @Setter
    @Getter
    private ElevatorStates elevatorState = WAITING;
    @Getter
    private Integer currentWeight = 0;
    @Setter
    @Getter
    private  Boolean elevatorDirection = false;
    private final List<Human> humanList = new ArrayList<>();
    private final Set<Integer> targetFloorSet = new TreeSet<>();
    @Setter
    @Getter
    private Boolean elevatorOverloading = false;
    public Elevator(Integer maxCapacity){
        this.maxCapacity = maxCapacity;
        this.setElevatorState(WAITING);
    }

    public synchronized Set<Integer> getTargetFloorSet() {
        return targetFloorSet;
    }

    public void setDefaultFloor(Integer defaultFloor) {
        this.defaultFloor = defaultFloor;
        this.currentFloor = defaultFloor;
    }

    public synchronized Integer getCurrentFloor() {
        return currentFloor;
    }

    @SneakyThrows
    private void closeDoors() {
        log.info(this + " close doors");
        Thread.sleep(1000);
    }
    @SneakyThrows
    private void openDoors(){
        log.info(this + " open doors");
        Thread.sleep(1000);
    }

    @SneakyThrows
     private   void elevatorProcessing(){
       while (true) {
           if (this.getTargetFloorSet().size() == 0 && humanList.size() == 0) {
                setElevatorInDefaultState();
           } else {
               try{
                   Integer targetFloor = getNextTargetFloor();
                   if (this.currentFloor.equals(targetFloor)) {
                        floorProcess();
                   } else {
                        moving(currentFloor, targetFloor);
                   }
               }
               catch (ConcurrentModificationException ex){
                   log.warn("FloorSetInProcess! Try again.");
               }
           }
       }
     }

     private void setElevatorInDefaultState(){
         if(!getCurrentFloor().equals(this.defaultFloor)) {
             this.currentFloor = defaultFloor;
         }
         this.setElevatorState(WAITING);
     }
     @SneakyThrows
     private synchronized Integer getNextTargetFloor() {
         return getElevatorDirection().equals(false) ? Collections.max(getTargetFloorSet()): Collections.min(getTargetFloorSet());
    }

     @SneakyThrows
     private void moving(Integer currentFloor,Integer targetFloor){
            Thread.sleep(1000);
            if (!this.currentFloor.equals(targetFloor)){
            this.currentFloor = currentFloor > targetFloor ? currentFloor - 1: currentFloor +1;
            log.info(this + "elevator is on " + this.getCurrentFloor() + " floor");}
     }

    private  void addHumans(){
        Floor floor = getReadyBuilding().floorMap.get(this.currentFloor);
        Queue<Human> humanQueue = getElevatorDirection() ? floor.getUpQueue() : floor.getDownQueue();
        while (humanQueue.size()!= 0 || getMaxCapacity() < (getCurrentWeight() + 40) ) {
            if(humanQueue.size() == 0 ){
               processEmptyFloorQueue();
               break;
            }
            else {
               this.setElevatorState(BUSY);
               if(processNotEmptyFloorQueue(humanQueue))break;
            }
        }
    }
    private Boolean processNotEmptyFloorQueue(Queue<Human> humanQueue){
        if(!checkOverload(humanQueue.peek().getWeight())) {
            humanIntoElevator(humanQueue);
        }
        else{
            Overloading();
            return true;
        }
        return false;
    }

    private void processEmptyFloorQueue(){
        if(humanList.size() == 0 && this.getTargetFloorSet().size() == 0) {
            this.setElevatorState(WAITING);
        }
        else {
            this.setElevatorState(BUSY);
            this.targetFloorSet.remove(this.currentFloor);
        }
    }
    private void humanIntoElevator(Queue<Human> humanQueue){
        setElevatorOverloading(false);
        getTargetFloorSet().add(humanQueue.peek().getTargetFloor());
        this.currentWeight += humanQueue.peek().getWeight();
        log.info("Elevator: " + this
                +" Human: " +  humanQueue.peek()
                + " go in on "+ getCurrentFloor().toString()
                + "->"+humanQueue.peek().getTargetFloor().toString());
        this.humanList.add(humanQueue.poll());}

    private  void Overloading(){
        log.warn("Elevator: " + this +" No free space");
        setElevatorOverloading(true);
        this.targetFloorSet.remove(this.currentFloor);
    }
    private Boolean checkOverload(Integer humanWeight){
        return (getCurrentWeight() + humanWeight) > getMaxCapacity();
    }

    @Override
    public String toString() {
        return "Elevator@" +this.hashCode();
    }

    @SneakyThrows
    private  void deleteHumans(){
        if (this.humanList.size()!= 0 ){
            List<Human> toDel= new ArrayList<>();
            this.humanList.forEach(human -> {
                if (human.getTargetFloor().equals(getCurrentFloor())) {
                    log.info("Elevator: " + this + " Human: " + human + " go out on "
                            + getCurrentFloor().toString());
                    toDel.add(human);

                }
            });
            toDel.forEach(h->{
                this.currentWeight = currentWeight - h.getWeight();
                this.humanList.remove(h);});
        }
    }

    private void floorProcess(){
        openDoors();
        deleteHumans();
        addHumans();
        deleteHumans();
        closeDoors();
        this.getTargetFloorSet().remove(getCurrentFloor());
    }

    @Override
    public void run() {
        elevatorProcessing();
    }
}

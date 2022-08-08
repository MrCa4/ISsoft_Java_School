package services;

import domain.Building;
import domain.elevator.Elevator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static domain.elevator.ElevatorStates.BUSY;

@Slf4j
public class ElevatorController implements Runnable {

    private static Integer responsibilityZoneLength;
    private static List<Elevator> elevatorList;
    private static final Map<Integer,Elevator> elevatorMap = new HashMap<>();
    public static Set<Integer> upCalls = new HashSet<>();
    public static Set<Integer> downCalls = new HashSet<>();
    private static final Comparator<Integer> compareMore = Comparator.comparingInt(p -> p);
    private static final Comparator<Integer> compareLess =  (p1, p2) -> p2-p1;
    private static ElevatorController elevatorController = null;

    private ElevatorController(){
        initProcess();
    }

    public static ElevatorController getElevatorController(){
        if (elevatorController==null){
            elevatorController = new ElevatorController();
        }
        return elevatorController;
    }

    private static void initProcess(){
        elevatorList = Building.getReadyBuilding().elevatorList;
        Integer responsibilityZoneCount = Building.getReadyBuilding().getElevatorCount();
        responsibilityZoneLength = Building.getReadyBuilding().getLevelCount() /
                Building.getReadyBuilding().getElevatorCount();
        int elevatorZoneOffset = responsibilityZoneLength / 2;
        for(int i = responsibilityZoneCount; i > 0; i--){
            elevatorList.get(i - 1).setElevatorZoneNumber(i);
            elevatorList.get(i - 1).setDefaultFloor((i - 1)
                    * responsibilityZoneLength + elevatorZoneOffset);
            elevatorMap.put(i, elevatorList.get(i - 1));
        }
    }

    private  void checkElevatorCall(){
        Building.getReadyBuilding().floorList.parallelStream().forEach(floor->{
            if (floor.getDownQueue().size()!=0){downCalls.add(floor.getFloorNumber());}
            if (floor.getUpQueue().size()!=0){upCalls.add(floor.getFloorNumber());}
        });
    }

    private void callCollector(){
        checkElevatorCall();
        if (downCalls.size()!=0){
        selectElevatorForCall(compareMore,downCalls,false);}
        if(upCalls.size()!=0){
        selectElevatorForCall(compareLess,upCalls,true);}
    }

    private void selectElevatorForCall(Comparator comparator,Set<Integer> calls,Boolean direction){
        List<Elevator> emergencyElevators = new ArrayList<>();
        int callFloor = direction ? Collections.min(calls) : Collections.max(calls);
        Elevator responsibleElevator = elevatorMap.get(callFloor/responsibilityZoneLength == 0
                                                        ?1:callFloor/responsibilityZoneLength);
        emergencyElevators.addAll(elevatorMap.values());
        emergencyElevators.remove(responsibleElevator);
        if (!callProcessing(comparator, responsibleElevator, callFloor, calls, direction)){
            for (Elevator elevator :emergencyElevators){
                if(callProcessing(comparator, elevator, callFloor, calls, direction)){
                    break;
                }
            }
        }
        emergencyElevators.clear();
    }

    private boolean callProcessing(Comparator comparator,Elevator responsibleElevator
            , Integer callFloor, Set<Integer> calls, Boolean direction){
            if ((responsibleElevator.getElevatorState() == BUSY)){
                if(responsibleElevator.getElevatorDirection() == direction
                    && comparator.compare(responsibleElevator.getCurrentFloor(),callFloor)>0){
                   addAllPossibleCals(calls, responsibleElevator);
                    return true;
                }
                return false;
            }
            else {
                responsibleElevator.setElevatorDirection(direction);
                addAllPossibleCals(calls, responsibleElevator);
                return true;
            }
    }
    private void addAllPossibleCals(Set<Integer> calls, Elevator elevator) {
        elevator.setElevatorState(BUSY);
        calls.forEach(targetFloorNumber ->{
            addElevatorTarget(elevator, targetFloorNumber);
        });

    }

    @SneakyThrows
    private void addElevatorTarget(Elevator elevator,Integer floor){
        elevator.getTargetFloorSet().add(floor);
    }

    @Override
    @SneakyThrows
    public void run() {
        log.warn("Starting elevator controller");
        while (true){
            callCollector();
        }
    }
}

package services;

import domain.Building;
import domain.Floor;
import domain.Human;
import lombok.SneakyThrows;
import lombok.Setter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

@Slf4j
public class HumanGenerator implements Runnable {

    @Setter
    private static Integer floorCount = Building.getReadyBuilding().getLevelCount();

    @Setter
    @Getter
    private static double firstFloorLoadFactor =  1.0;
    private static final Random random = new Random();
    private static final List<Floor> floorList = Building.getReadyBuilding().floorList;
    @Setter
    private static int generationPeriodSeconds = 10;

    private static  HumanGenerator humanGenerator = null;

    private HumanGenerator(){
    }

    public static HumanGenerator getHumanGenerator(){
        if (humanGenerator == null){
            humanGenerator = new HumanGenerator();
        }
        return humanGenerator;
    }

    private void addNewHumanToQueue(){
        int floor_queue = random.nextInt((int)(floorCount*firstFloorLoadFactor)) + 1;

        //Таким образом искуственно повышаем вероятность людей на 1 этаже.
        floor_queue = floor_queue > floorCount ? 1 : floor_queue;
        Floor floor = floorList.get(floor_queue-1);
        Human human = generateHuman();
        log.info("Generate human: " + human);
        if (floor_queue > 1 && floor_queue < floorCount){
            boolean nop = human.getTargetFloor() < floor_queue
                    ? floor.getDownQueue().add(human) : floor.getUpQueue().add(human);
        } else if (floor_queue == 1) {
            floor.getUpQueue().add(human);
        }
        else if (floor_queue == floorCount) {
            floor.getDownQueue().add(human);
        }
    }

    private Human generateHuman(){
        return new Human(Human.MINIMUM_HUMAN_WEIGHT + random.nextInt(70)
        , random.nextInt(floorCount-1)+1);
    }

    @Override
    @SneakyThrows
    public void run() {
        log.warn("Starting humanSpawner!");
        while(true){
            addNewHumanToQueue();
            sleep(generationPeriodSeconds * 1_000L);
        }

    }
}

import domain.Building;
import lombok.extern.slf4j.Slf4j;
import services.Builder;
import services.ElevatorController;
import services.HumanGenerator;

import static services.ElevatorController.*;
import static services.HumanGenerator.*;



@Slf4j
public class ElevatorsApp {

    //public static slf4jLog = org.slf4j.LoggerFactory.getLogger(LoggingSlf4j.class);

    public static void main(String[] args) {
        Builder builder  = new Builder();
        builder.build(30,3, 680);

        //Start humanGenerator
        HumanGenerator humanGenerator = getHumanGenerator();
        setGenerationPeriodSeconds(3);
        setFirstFloorLoadFactor(1.34);
        log.info("Create humanSpawner");
        Thread humanGeneratorThready = new Thread(humanGenerator);
        humanGeneratorThready.start();

        ElevatorController elevatorController = getElevatorController();
        Thread elevatorControllerThread = new Thread(elevatorController);
        elevatorControllerThread.start();
        Building.getReadyBuilding().elevatorList.forEach(elevator -> new Thread(elevator).start());
    }
}

package train.trainStates;

import cargoCompany.Company;
import lombok.extern.slf4j.Slf4j;
import train.Train;
import train.wagon.entity.WagonEntity;

import java.util.List;


public class EmptyTrainDriverLocomotive extends TrainState {

    public EmptyTrainDriverLocomotive(Train train) {
        super(train);
    }
    @Override
    public void run() {
         defaultAnswer();
    }

    @Override
    public void stop() {
         defaultAnswer();
    }

    @Override
    public void addDriver() {
         defaultAnswer();
    }

    @Override
    public void addLocomotive() {
        defaultAnswer();
    }


    @Override
    public void addWagons() {
        this.train.addWagons();
        this.train.changeState(new EmptyTrainDriverLocomotiveWagons(this.train));
        Company.logger.info("Wagons generated and connected!");

    }

    @Override
    public void addWagonEntities(List<WagonEntity> wagonEntity) {
         defaultAnswer();
    }

    @Override
    public void defaultAnswer() {
        Company.logger.info("First off add wagons!");
    }
}

package train.trainStates;

import cargoCompany.Company;
import lombok.extern.slf4j.Slf4j;
import train.Train;
import train.wagon.entity.WagonEntity;

import java.util.List;


public class EmptyTrainDriverLocomotiveWagons extends TrainState {


    public EmptyTrainDriverLocomotiveWagons(Train train) {
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
        defaultAnswer();
    }

    @Override
    public void addWagonEntities(List<WagonEntity> wagonEntity) {
        this.train.addTrainEntities(wagonEntity);
        this.train.changeState(new FullTrain(this.train));
        Company.logger.info("Our train get entities! We ready to start our way!)");
    }

    @Override
    public void defaultAnswer() {
        Company.logger.info("Need to add entities in wagons");
    }
}

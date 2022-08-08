package train.trainStates;

import cargoCompany.Company;
import lombok.extern.slf4j.Slf4j;
import train.Train;
import train.wagon.entity.WagonEntity;

import java.util.List;


public class EmptyTrain extends TrainState {


    public EmptyTrain(Train train) {
        super(train);
        // train.setPlaying(false);
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
        this.train.changeState(new EmptyTrainDriver(train) );
        Company.logger.info("Add new driver successfully!");
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
         defaultAnswer();
    }

    @Override
    public void defaultAnswer() {
        Company.logger.info("Train is empty! Add locomotive driver at first");
    }
}

package train.trainStates;

import cargoCompany.Company;
import lombok.extern.slf4j.Slf4j;
import train.Train;
import train.wagon.entity.WagonEntity;

import java.util.List;


public class FullTrain extends TrainState {
    public FullTrain(Train train) {
        super(train);
    }

    @Override
    public void run() {
        train.changeState(new TrainInWay(train));
        Company.logger.info("Ready!?\r\nStady!\r\nGO!!!");
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
         defaultAnswer();
    }

    @Override
    public void defaultAnswer() {
        Company.logger.info("Train ready to start! ");
    }
}

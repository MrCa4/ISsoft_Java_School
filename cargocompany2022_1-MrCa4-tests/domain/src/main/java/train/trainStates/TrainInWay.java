package train.trainStates;

import cargoCompany.Company;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import train.Train;
import train.wagon.entity.WagonEntity;

import java.util.List;


public class TrainInWay extends TrainState  {
    public TrainInWay(Train train) {
        super(train);
    }

    @Override
    public void run() {
        defaultAnswer();
    }

    @Override
    public void stop() {
        train.changeState(new FullTrain(train));
        Company.logger.info("We end our trip!  See You Later");
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
        Company.logger.info("Wait! we in way!");
    }
}

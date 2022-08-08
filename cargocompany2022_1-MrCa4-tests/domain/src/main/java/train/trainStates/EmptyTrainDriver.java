package train.trainStates;

import cargoCompany.Company;
import lombok.extern.slf4j.Slf4j;
import train.Train;
import train.wagon.entity.WagonEntity;

import java.util.List;


public class EmptyTrainDriver extends TrainState {


    public EmptyTrainDriver(Train train) {
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
         defaultAnswer();
    }

    @Override
    public void addLocomotive() {
        if(!this.train.addLocomotive(this.train.getWagonCounts(),this.train.getLocomotiveDriver())){
            Company.logger.warning("Error to create locomotive");
        }
        this.train.changeState(new EmptyTrainDriverLocomotive(train) );
        Company.logger.info("Excellent we have locomotive for " + this.train.getWagonCounts() +" wagons");
    }

    @Override
    public void addWagons() {
         defaultAnswer();
    }

    @Override
    public void addWagonEntities(List<WagonEntity> wagonEntityList) {
        defaultAnswer();
    }

    public void defaultAnswer(){
        Company.logger.info("Hello! I am your locomotive driver! My name is "
                +this.train.getLocomotiveDriver().name
                +"! Please add locomotive ");
    }
}

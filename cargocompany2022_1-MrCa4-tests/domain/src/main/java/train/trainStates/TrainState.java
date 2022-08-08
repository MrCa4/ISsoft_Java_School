package train.trainStates;

import train.Train;
import train.wagon.entity.WagonEntity;

import java.util.List;

public abstract class TrainState {

    Train train;


    public TrainState(Train train) {
        this.train = train;
    }
    public abstract void run();
    public abstract void stop();
    public abstract void addDriver();
    public abstract void addLocomotive();
    public abstract void addWagons();
    public abstract void addWagonEntities(List<WagonEntity> wagonEntityList);
    public abstract void defaultAnswer();
}

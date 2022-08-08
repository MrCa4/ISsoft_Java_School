package train.wagon;

import train.TrainChain;
import train.wagon.entity.WagonEntity;

public interface Wagon {

    void setWagonNumber(int wagonNumber);
    void setEntity(WagonEntity wagonEntity);
    int getWagonNumber();
    void setWagonMaxParam(int wagonMaxParam);
    Class getWagonType();
    TrainChain getTrainChain();

}

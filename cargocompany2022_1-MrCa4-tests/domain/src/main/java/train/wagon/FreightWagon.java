package train.wagon;

import train.TrainChain;
import train.wagon.entity.Cargo;
import train.wagon.entity.WagonEntity;

import java.util.ArrayList;
import java.util.List;

public class FreightWagon implements Wagon {

    public TrainChain trainChain;
    List<Cargo> cargoList = new ArrayList<>();

    public FreightWagon(){
        this.trainChain = new TrainChain(this);
    }
    @Override
    public TrainChain getTrainChain() { return trainChain; }


    @Override
    public void setWagonNumber(int wagonNumber) {

    }

    @Override
    public void setEntity(WagonEntity wagonEntity) {
        cargoList.add((Cargo) wagonEntity);
    }

    @Override
    public int getWagonNumber() {
        return 0;
    }

    @Override
    public void setWagonMaxParam(int wagonMaxParam) {
    }

    public Class getWagonType() {
        return this.getClass();
    }



}

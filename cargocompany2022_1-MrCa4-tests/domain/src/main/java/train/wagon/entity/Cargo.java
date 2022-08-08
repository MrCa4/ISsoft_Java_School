package train.wagon.entity;

import train.Train;
import train.wagon.Wagon;

import java.util.UUID;

public class Cargo implements  WagonEntity{

    private int cargoWeight;
    public String uid;

    public Cargo(int cargoWeight){
        this.setCargoWeight(cargoWeight);
        this.uid = UUID.randomUUID().toString();
    }

    @Override
    public void completeWagon(Train myTrain) {
    }

    public int getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(int cargoWeight) {
        this.cargoWeight = cargoWeight;
    }
}

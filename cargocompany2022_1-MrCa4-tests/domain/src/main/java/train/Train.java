package train;

import cargoCompany.Company;
import train.trainStates.EmptyTrain;
import train.trainStates.TrainState;
import train.wagon.Locomotive;
import train.wagon.Wagon;
import train.wagon.entity.Cargo;
import train.wagon.entity.User;
import train.wagon.entity.WagonEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Train {


    private TrainState trainState;
    private String trainNumber;
    private User locomotiveDriver;
    private int wagonCounts;
    private TrainTypeEnum wagonType;
    public Locomotive locomotive;

    //TODO cycle object including!!!!!!!!!!

    //TODO may be should use pattern builder here???
    public  Train(String trainNumber,
                  int wagonCount,
                  User locomotiveDriver,
                  TrainTypeEnum type){
        this.locomotiveDriver = locomotiveDriver;
        this.wagonCounts = wagonCount;
        this.trainNumber=trainNumber;
        this.wagonType = type;
        this.trainState= new EmptyTrain(this);
    }

    public int getWagonCounts() { return wagonCounts; }
    public User getLocomotiveDriver() { return locomotiveDriver; }
    public void changeState(TrainState state) { this.trainState = state; }
    public TrainState getState() { return trainState; }
    public TrainTypeEnum getWagonType() {
        return wagonType;
    }
    public String getTrainNumber() {
        return trainNumber;
    }

    //Refactor !
    public boolean addLocomotive(int wagonCount, User locomotiveDriver){
        Locomotive locomotive = new Locomotive(wagonCount, locomotiveDriver);
        if (locomotive.readyStatus){
             this.locomotive = locomotive;
             return true;
        }
        return false;
    }



    public void addWagons(){
        Wagon currentWagon;
        Wagon priveosWagon = this.locomotive;
        for(int currentWagonNumber = 1; currentWagonNumber < this.getWagonCounts()+1; currentWagonNumber++){
            try {
                currentWagon = (Wagon) this.getWagonType().getWagon().getConstructor().newInstance();
                currentWagon.setWagonNumber(currentWagonNumber);
                currentWagon.setWagonMaxParam(wagonType.getMaxParam());
                priveosWagon.getTrainChain().setNextWagon(currentWagon);
                currentWagon.getTrainChain().setPreviousWagon(priveosWagon);
                priveosWagon=currentWagon;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                Company.logger.warning("Error while generating new wagon!");
                e.printStackTrace();
            }

        }

    }

    public void addTrainEntities(List<WagonEntity> wagonEntities){

        if (this.getWagonType().equals(TrainTypeEnum.PASSENGER_PLACKART) ||
        this.getWagonType().equals(TrainTypeEnum.PASSENGER_KUPE)) {
            Company.logger.info("Get passenger train. Prepare for add entities");
            wagonEntities.forEach(entity -> entity.completeWagon(this));
        }
        else {
            Company.logger.info("Get cargo train. Prepare for add entities");
            Wagon currentWagon = this.locomotive.getTrainChain().nextWagon;
            int wholeWeight = wagonEntities.stream().mapToInt(i -> ((Cargo) i).getCargoWeight()).sum();
            wagonEntities.sort((o1, o2) -> ((Cargo) o1).getCargoWeight() > (((Cargo) o2).getCargoWeight()) ? 1 : 0);
            List<WagonEntity> tempCargoList = new ArrayList<>(wagonEntities);
            List<WagonEntity> tempCargoListForDelete = new ArrayList<>();
            while (wholeWeight != 0) {
                int wagonFreeSpace = this.getWagonType().getMaxParam();
                for (WagonEntity cargo : tempCargoList) {
                    if (wagonFreeSpace > ((Cargo)cargo).getCargoWeight()) {
                        wagonFreeSpace -= ((Cargo)cargo).getCargoWeight();
                        wholeWeight -= ((Cargo)cargo).getCargoWeight();
                        tempCargoListForDelete.add(cargo);
                    }
                }
                tempCargoListForDelete.forEach(tempCargoList::remove);
                currentWagon = currentWagon.getTrainChain().getNextWagon();
            }
        }
    }
}













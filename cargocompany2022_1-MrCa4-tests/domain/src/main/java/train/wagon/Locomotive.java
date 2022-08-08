package train.wagon;

import train.TrainChain;
import train.wagon.entity.User;
import train.wagon.entity.WagonEntity;

import static cargoCompany.securityDepartmentServices.DriverInspection.checkDriver;

public class Locomotive implements Wagon {

    private int maxWagonPower;
    private User driver;
    public boolean readyStatus = false;
    private TrainChain trainChain;

    public Locomotive(int wagonCount, User driver){
        this.trainChain = new TrainChain(this);
        this.maxWagonPower=wagonCount;
        if (checkDriver(driver)) {
            this.driver = driver;
            readyStatus=true;
        }
        else{ System.out.println("Bad driver"); }
    }

    public int getMaxWagonPower() {
        return maxWagonPower;
    }
    public TrainChain getTrainChain() { return trainChain; }
    public User getDriver() {
        return driver;
    }

    @Override
    public void setWagonNumber(int wagonNumber) {
    }

    @Override
    public void setEntity(WagonEntity trainEntity) {
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

package train;

import cargoCompany.Company;
import train.wagon.Wagon;


/**
 * My implementation of LinkedList
 */

public class TrainChain {
    Wagon nextWagon;
    Wagon previousWagon;
    Wagon currentWagon;

    public  TrainChain(Wagon wagon){
        currentWagon=wagon;
    }

    public Wagon getNextWagon() {
        if (checkLastWagon(nextWagon)){
            Company.logger.info("This is last wagon!");
            return null;
        }
        return nextWagon;
    }

    public void setNextWagon(Wagon nextWagon) {
        this.nextWagon = nextWagon;
    }

    public Wagon getPreviousWagon() {
        if (checkLastWagon(previousWagon)){
            Company.logger.info("This is locomotive - first wagon!");
            return null;
        }
        return previousWagon;
    }

    public void setPreviousWagon(Wagon previousWagon) {
        this.previousWagon = previousWagon;
    }

    public void disconnectCurrentWagon(){
        if(checkLastWagon(previousWagon)){
            Company.logger.info("This is locomotive, we cant do it");
        }
        if (checkLastWagon(nextWagon)){
            previousWagon.getTrainChain().setNextWagon(null);
        }
        else {
            previousWagon.getTrainChain().setNextWagon(nextWagon);
            nextWagon.getTrainChain().setPreviousWagon(previousWagon);
        }
    }

    public void connectCurrentWagon(Wagon wagon){
        previousWagon = wagon;
        wagon.getTrainChain().setNextWagon(currentWagon);
    }

    public void connectCurrentWagon(Wagon previous_Wagon,Wagon next_Wagon){
        nextWagon=next_Wagon;
        previousWagon = previous_Wagon;
        next_Wagon.getTrainChain().setPreviousWagon(currentWagon);
        previous_Wagon.getTrainChain().setNextWagon(currentWagon);
    }

    private boolean checkLastWagon(Wagon wagon){
        return wagon == null;
    }

}

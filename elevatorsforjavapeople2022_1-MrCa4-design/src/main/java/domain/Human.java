package domain;

public class Human {

    @Override
    public String toString() {
        return "Human@" + this.hashCode() +
                 "targetFloor="+ targetFloor;
    }

    public static final int MINIMUM_HUMAN_WEIGHT = 40;

    private final Integer weight ;
    private final Integer targetFloor ;

    public Human(Integer weight, Integer targetFloor){
        this.weight = weight;
        this.targetFloor = targetFloor;
    }

    public synchronized Integer getWeight() {
        return weight;
    }

    public synchronized Integer getTargetFloor() {
        return targetFloor;
    }
}

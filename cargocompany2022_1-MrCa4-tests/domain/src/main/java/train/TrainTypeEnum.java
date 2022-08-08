package train;

import train.wagon.FreightWagon;
import train.wagon.PassengerWagon;

public enum TrainTypeEnum {

    FREIGHT_STANDART(FreightWagon.class,400),
    FREIGHT_MAX(FreightWagon.class,600),
    PASSENGER_PLACKART(PassengerWagon.class,20),
    PASSENGER_KUPE(PassengerWagon.class,10);

    Class wagon;
    private Integer maxParam;

    public int getMaxParam() {
        return maxParam;
    }

    public Class getWagon() {
        return wagon;
    }

    TrainTypeEnum(Class wagon, Integer maxParam) {
        this.maxParam = maxParam;
        this.wagon =wagon;
    }
}

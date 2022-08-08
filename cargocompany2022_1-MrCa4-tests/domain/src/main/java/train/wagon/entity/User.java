package train.wagon.entity;


import train.Train;
import train.Ticket;
import train.wagon.Wagon;

public class User implements WagonEntity{
    public String name;
    public String lastName;
    public int age;
    public Boolean trainLicense;
    public Ticket myTicket = new Ticket();
    public boolean criminal=false;

    //TODO change to builder
    public User(String name, String lastName, int age, Boolean trainLicense, Boolean criminal){
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.trainLicense = trainLicense;
        this.criminal=criminal;
    }

    public User(String name,String lastName, int age, Boolean trainLicense){
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.trainLicense = trainLicense;
    }


    public void  buyTicket(Train train){
        myTicket.setTotalSeatsCount(train.getWagonType().getMaxParam());
        myTicket.setTotalWagonCount(train.getWagonCounts());
        myTicket.setTrainNumber(train.getTrainNumber());
        myTicket.setPassengerName(this.name);
        myTicket.setPassengerSurname(this.lastName);
    }
    @Override
    public void completeWagon(Train myTrain){
        this.buyTicket(myTrain);
        Wagon curentWagon = myTrain.locomotive.getTrainChain().getNextWagon();
        while(curentWagon.getWagonNumber()!=this.myTicket.getWagonNumber()){
            curentWagon = curentWagon.getTrainChain().getNextWagon();
        }
        curentWagon.setEntity(this);
    }


}

package train;

import java.util.Random;

public class Ticket {

    private int totalSeatsCount;



    private int totalWagonCount;
    private String trainNumber;
    private  int wagonNumber;
    private int seatNumber;
    private String passengerName;
    private String passengerSurname;


    public int getTotalSeatsCount() {
        return totalSeatsCount;
    }
    public void setTotalSeatsCount(int totalSeatsCount) {
        this.totalSeatsCount = totalSeatsCount;
        this.seatNumber=(new Random().nextInt(totalSeatsCount)+1);
    }
    public int getTotalWagonCount() {
        return totalWagonCount;
    }
    public void setTotalWagonCount(int totalWagonCount) {
        this.totalWagonCount = totalWagonCount;
        this.wagonNumber=(new Random().nextInt(totalWagonCount)+1);
    }

    public String getTrainNumber() {
        return trainNumber;
    }
    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }
    public int getWagonNumber() {
        return wagonNumber;
    }
    public int getSeatNumber() {
        return seatNumber;
    }
    public String getPassengerName() {
        return passengerName;
    }
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }
    public String getPassengerSurname() {
        return passengerSurname;
    }
    public void setPassengerSurname(String passengerSurname) {
        this.passengerSurname = passengerSurname;
    }
}

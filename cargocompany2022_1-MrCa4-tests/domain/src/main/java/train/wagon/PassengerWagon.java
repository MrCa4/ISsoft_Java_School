package train.wagon;

import cargoCompany.Company;
import cargoCompany.securityDepartmentServices.PassengerChecker;
import cargoCompany.securityDepartmentServices.ticketValidators.ComparePassengerTicketsChecker;
import cargoCompany.securityDepartmentServices.ticketValidators.CriminalChecker;
import cargoCompany.securityDepartmentServices.ticketValidators.FreePlacesChecker;
import cargoCompany.securityDepartmentServices.ticketValidators.TicketChecker;
import train.TrainChain;
import train.wagon.entity.User;
import train.wagon.entity.WagonEntity;

import java.util.HashMap;
import java.util.Map;

public class PassengerWagon implements Wagon {

    private int wagonNumber;
    public TrainChain trainChain;
    private Map<Integer, User> ticketsMap = new HashMap<>();
    private int maxCountOfPassengers;

    public PassengerWagon(){
        this.trainChain = new TrainChain(this);
    }

    @Override
    public void setWagonMaxParam(int wagonMaxParam) { this.maxCountOfPassengers = wagonMaxParam; }
    public void setWagonNumber(int wagonNumber) { this.wagonNumber = wagonNumber; }
    @Override
    public int getWagonNumber() {
        return wagonNumber;
    }
    public int getMaxCountOfPassengers() {
        return maxCountOfPassengers;
    }
    public Map<Integer, User> getTicketsMap() {
        return ticketsMap;
    }
    public TrainChain getTrainChain() { return trainChain; }
    public Class getWagonType() {
        return this.getClass();
    }

    @Override
    public void setEntity(WagonEntity entity){
            User user = (User) entity;
            if(addUser(user)){
                ticketsMap.put(user.myTicket.getSeatNumber(),user);
                Company.logger.info(user.name + " we glad to see you in " + user.myTicket.getWagonNumber() +
                        " wagon and " + user.myTicket.getSeatNumber() + " seat!");
            }
    }

    //Chain of responsibilities...
    /** Попробовал реализовать цепочку проверки билетов у пассажиров.
     * Если хотя бы одна из стандартных проверок не проходит мы начинаем проверять
     * пассажира подробнее. Сразу проверяем не мошенник ли он, если да отправляем его в полицию,
     * где его задерживают. Если же он не криминал и билет совпадает с местом, но оно занято,
     * мы смотри не мошенник ли тот, кто уже занял место, если же и он не мошенник, данный
     * пассажир передается в отдел решения проблем -> находим ближайшее свободное место и садим его.
     * Возможно не лучший алгоритм (такого пассажира надо либо ставить в конец очереди, либо отслеживать
     * проданные билеты и садить на точно свободное место, а если мест нет добовлять вагон и садить туда),
     * но как есть...*/
    public Boolean addUser(User user){
        PassengerChecker standartProcedure = new FreePlacesChecker();
        standartProcedure.linkNextValidator(new TicketChecker());
        if(!standartProcedure.check(this, user)) {
            PassengerChecker powerfullProcedure = new CriminalChecker();
            powerfullProcedure.linkNextValidator(new TicketChecker());
            powerfullProcedure.linkNextValidator(new ComparePassengerTicketsChecker());
            powerfullProcedure.check(this,user);
        }
        return true;
    }


}

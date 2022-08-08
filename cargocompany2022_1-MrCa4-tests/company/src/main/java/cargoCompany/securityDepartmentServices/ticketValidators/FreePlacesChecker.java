package cargoCompany.securityDepartmentServices.ticketValidators;

import cargoCompany.Company;
import cargoCompany.securityDepartmentServices.PassengerChecker;
import train.wagon.PassengerWagon;
import train.wagon.entity.User;

public class FreePlacesChecker extends PassengerChecker {

    public boolean check(PassengerWagon wagon, User user) {

        if (wagon.getTicketsMap().values().size() < wagon.getMaxCountOfPassengers() ){
                if(wagon.getTicketsMap().get(user.myTicket.getSeatNumber()) == null ) {
                    return checkNext(wagon, user);
                }
            Company.logger.info("Passenger " + user.name +" seat is busy. Need strong verification");
                return false;
        }
        Company.logger.info("The wagon is Full! Please call Conflict Resolution service;");
        return false;
    }


}

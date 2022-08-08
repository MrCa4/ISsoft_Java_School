package cargoCompany.securityDepartmentServices.ticketValidators;

import cargoCompany.securityDepartmentServices.PassengerChecker;
import train.wagon.PassengerWagon;
import train.wagon.entity.User;

public class TicketChecker extends PassengerChecker {


    public boolean check(PassengerWagon wagon, User user) {
        return user.myTicket.getWagonNumber() == wagon.getWagonNumber() &&
                user.name.equals(user.myTicket.getPassengerName()) &&
                user.lastName.equals(user.myTicket.getPassengerSurname());
    }
}

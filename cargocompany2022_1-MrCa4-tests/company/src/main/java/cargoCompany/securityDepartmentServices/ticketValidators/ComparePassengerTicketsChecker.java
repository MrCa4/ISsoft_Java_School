package cargoCompany.securityDepartmentServices.ticketValidators;

import cargoCompany.Company;
import cargoCompany.securityDepartmentServices.ConflictResolution;
import cargoCompany.securityDepartmentServices.PassengerChecker;
import cargoCompany.securityDepartmentServices.PoliceService;
import train.wagon.PassengerWagon;
import train.wagon.entity.User;

public class ComparePassengerTicketsChecker extends PassengerChecker {
    public boolean check(PassengerWagon wagon, User user) {
        Company.logger.info("Compare ticket of new passenger and passenger already in wagon.");
        User passengerOnSeat = wagon.getTicketsMap().get(user.myTicket.getSeatNumber());
        if (passengerOnSeat!= null ){
            Company.logger.info("No passenger on this seat");
            if (passengerOnSeat.criminal) {
                Company.logger.info("Alert! We found criminal passenger! Call Police!");
                PoliceService.getPolicyService().addCriminal(passengerOnSeat);
            }
            else
                ConflictResolution.getConflictResolutionService().resoluteDoubleTicketOnOnePlaceSituation();
        }
        return true;
    }
}

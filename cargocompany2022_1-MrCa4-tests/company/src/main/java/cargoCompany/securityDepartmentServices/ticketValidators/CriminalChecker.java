package cargoCompany.securityDepartmentServices.ticketValidators;

import cargoCompany.Company;
import cargoCompany.securityDepartmentServices.PassengerChecker;
import cargoCompany.securityDepartmentServices.PoliceService;
import train.wagon.PassengerWagon;
import train.wagon.entity.User;

public class CriminalChecker extends PassengerChecker {

    public boolean check(PassengerWagon wagon, User user) {
        if (user.criminal) {
            Company.logger.info("Add criminal user to prison.");
            PoliceService.getPolicyService().addCriminal(user);
            return false;
        }
        return checkNext(wagon,user);
    }
}

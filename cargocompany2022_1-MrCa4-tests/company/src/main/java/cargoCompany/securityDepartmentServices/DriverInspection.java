package cargoCompany.securityDepartmentServices;

import cargoCompany.Company;
import train.wagon.entity.User;

public class DriverInspection {
    public static boolean checkDriver(User user){
        Company.logger.info("Check driver license and age.");
        if (user.age > 18 && user.trainLicense){

            return true;
        }
        Company.logger.info("Bad driver!");
        return false;

    }
}

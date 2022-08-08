import cargoCompany.Company;
import cargoCompany.TrainDepartment;
import train.TrainTypeEnum;

import java.util.logging.Logger;

public class App {

    public static final Logger logger = Logger.getLogger(App.class.getName());


    public static void main(String[] args) {
        logger.info("Start App");
        Company.getCompany().start();
    }
}

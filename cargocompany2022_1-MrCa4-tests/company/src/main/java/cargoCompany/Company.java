package cargoCompany;

import cargoCompany.randomPopulator.RandomCargoPopulator;
import cargoCompany.randomPopulator.RandomUserPopulator;
import cargoCompany.utils.CargoCalculator;
import train.Train;
import train.TrainTypeEnum;
import train.wagon.entity.User;
import java.util.UUID;
import java.util.logging.Logger;


public class Company {
    public static final Logger logger = Logger.getLogger(Company.class.getName());

    private static Company company=null;
    private Company(){
    }

    public static Company getCompany(){
        if(company == null) {
            company=new Company();
        }
        return company;
    }

    //Same test in trainDepartmentTest
    public void start() {
        logger.info("Start company presentation");
        RandomUserPopulator randomUserPopulator = new RandomUserPopulator();
        RandomCargoPopulator randomCargoPopulator = new RandomCargoPopulator();
        //Start passenger train

        Train train = new Train(UUID.randomUUID().toString(), 10,
                new User("Adam", "Badam", 22, true), TrainTypeEnum.PASSENGER_PLACKART);
        randomUserPopulator.generateUsers(train.getWagonCounts(),
                train.getWagonType().getMaxParam());
        TrainDepartment.getTrainDepartment().newTrain(train,randomUserPopulator.fakeUsers);
        //Start cargo train
        randomCargoPopulator.generateCargo(TrainTypeEnum.FREIGHT_STANDART.getMaxParam());
        Train train2 = new Train(UUID.randomUUID().toString(), CargoCalculator.cargoCalculation(TrainTypeEnum.FREIGHT_STANDART,randomCargoPopulator.fakeCargo),
                new User("Adam", "Badam", 22, true), TrainTypeEnum.FREIGHT_STANDART);
        TrainDepartment.getTrainDepartment().newTrain(train2,randomCargoPopulator.fakeCargo);

    }


}

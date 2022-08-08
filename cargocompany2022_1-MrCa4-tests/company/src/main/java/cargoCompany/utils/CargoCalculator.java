package cargoCompany.utils;

import cargoCompany.Company;
import train.TrainTypeEnum;
import train.wagon.entity.Cargo;
import train.wagon.entity.WagonEntity;

import java.util.ArrayList;
import java.util.List;

public class CargoCalculator {


    public static int cargoCalculation(TrainTypeEnum trainTypeEnum, List<WagonEntity> cargoList) {
        Company.logger.info("Calculating necessary wagon count for this cargo set.");
        List<WagonEntity> tempCargoList = new ArrayList<>(cargoList);
        List<WagonEntity> tempCargoListForDelete = new ArrayList<>();
        int wholeWeight = cargoList.stream().mapToInt(i -> ((Cargo)i).getCargoWeight()).sum();
        int wagonIterator = 0;
        while (wholeWeight != 0) {
            int wagonFreeSpace = trainTypeEnum.getMaxParam();

            for (WagonEntity cargo : tempCargoList) {
                if (wagonFreeSpace > ((Cargo)cargo).getCargoWeight()) {
                    wagonFreeSpace -= ((Cargo)cargo).getCargoWeight();
                    wholeWeight -= ((Cargo)cargo).getCargoWeight();
                    tempCargoListForDelete.add(cargo);
                }
            }
            tempCargoListForDelete.forEach(tempCargoList::remove);
            wagonIterator++;

        }
        return wagonIterator;

    }
}

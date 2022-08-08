package cargoCompany;

import train.Train;
import train.wagon.entity.WagonEntity;

import java.util.*;

public class TrainDepartment {
   public static Map<String,Train> trainList = new HashMap<>();
   private static TrainDepartment trainDepartment = null;

   private TrainDepartment(){
   }

   public static TrainDepartment getTrainDepartment() {
      if (trainDepartment == null){
          trainDepartment = new TrainDepartment();
      }
      return trainDepartment;
   }

   public void newTrain(Train train, List<WagonEntity> wagonEntityList){
      Company.logger.info("Create new Train");
      trainList.put(train.getTrainNumber(), train);
      addTrain(train, wagonEntityList);
   }
   private void addTrain(Train train, List<WagonEntity> wagonEntityList){
      train.getState().addDriver();
      train.getState().addLocomotive();
      train.getState().addWagons();
      train.getState().addWagonEntities(wagonEntityList);
      train.getState().run();
      train.getState().stop();
   }
}

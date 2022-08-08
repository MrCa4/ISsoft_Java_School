package cargoCompany.randomPopulator;

import cargoCompany.Company;
import com.github.javafaker.Faker;
import train.wagon.entity.Cargo;
import train.wagon.entity.WagonEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomCargoPopulator {
    public List<WagonEntity> fakeCargo= new ArrayList<>();
    public static Faker faker = new Faker();


    public void generateCargo(Integer maxParam){
        Company.logger.info("Generating fake cargo...");
        for(int i =0; i< 100;i++){

            fakeCargo.add(new Cargo(faker.number().numberBetween(20,maxParam)));


        }
    }
}

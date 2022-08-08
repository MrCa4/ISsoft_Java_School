package cargoCompany.randomPopulator;


import cargoCompany.Company;
import com.github.javafaker.Faker;
import train.wagon.entity.User;
import train.wagon.entity.WagonEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUserPopulator {

    public List<WagonEntity> fakeUsers= new ArrayList<>();
    public static Faker faker = new Faker();

    public void generateUsers(Integer wagonCount,Integer maxParam){
        Company.logger.info("Generating fake users...");
        for(int i =0; i< wagonCount*maxParam;i++){
            fakeUsers.add(new User(faker.name().name()
                    ,faker.funnyName().name()
                    ,new Random().nextInt(100)
                    ,new Random().nextBoolean()
                    ,new Random().nextBoolean()
                   ));
        }
    }
}

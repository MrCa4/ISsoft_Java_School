

import cargoCompany.TrainDepartment;
import cargoCompany.utils.CargoCalculator;
import org.junit.*;
import train.Train;
import train.TrainTypeEnum;
import train.wagon.entity.User;
import train.wagon.entity.WagonEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TrainDepartmentTest {

    private TrainDepartment trainDepartment ;

    @BeforeClass
    public static void globalSetUp() {
        System.out.println("Initial setup...");
    }

    @Before
    public void setUp() {
        System.out.println("Code executes before each test method");
    }

    @Test
    public void testTrainDepartmentInstance()  {
        trainDepartment = TrainDepartment.getTrainDepartment();
        Assert.assertEquals(trainDepartment,TrainDepartment.getTrainDepartment());

    }

    @Test
    public void testNewTrain(){
       Exception e=null;
       try {
           List<WagonEntity> testWagonEntityList = new ArrayList<>();
           Train train = new Train(UUID.randomUUID().toString()
                   , CargoCalculator.cargoCalculation(TrainTypeEnum.FREIGHT_STANDART, testWagonEntityList)
                   , new User("Test", "Test", 22, true)
                   , TrainTypeEnum.FREIGHT_STANDART);
           TrainDepartment.getTrainDepartment().newTrain(train, testWagonEntityList);
       }
       catch (Exception ex){
          e=ex;
       }
       finally {

           if (e != null) {
               Assert.assertTrue(e.getMessage().contains("Exception"));
           }
       }
    }


    @AfterClass
    public static void tearDown() {
        System.out.println("Tests finished");
    }

    @After
    public void afterMethod() {
        System.out.println("Code executes after each test method");
    }
}
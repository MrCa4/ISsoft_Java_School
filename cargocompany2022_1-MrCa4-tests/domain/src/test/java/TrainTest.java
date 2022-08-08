
import cargoCompany.TrainDepartment;
import cargoCompany.randomPopulator.RandomUserPopulator;
import org.junit.*;
import train.Train;
import train.TrainTypeEnum;
import train.wagon.entity.User;
import train.wagon.entity.WagonEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class TrainTest {

    private Train train ;

    @BeforeClass
    public static void globalSetUp() {
        System.out.println("Initial setup...");
    }

    @Before
    public void setUp() {
        System.out.println("Code executes before each test method");
    }

    @Test
    public void testTrainInstance()  {
        Exception e=null;
        try {
            train = new Train(UUID.randomUUID().toString(), 10,
                    new User("test", "test", 20, true), TrainTypeEnum.PASSENGER_PLACKART);
            Assert.assertEquals(train.getWagonType(),TrainTypeEnum.PASSENGER_PLACKART);
            train.addLocomotive(train.getWagonCounts(),train.getLocomotiveDriver());
            train.addWagons();
            List<WagonEntity> wagonEntityList = new ArrayList<>();
            User wagonEntity= new  User("test","test",20,false,false);
            wagonEntity.buyTicket(train);
            wagonEntityList.add(wagonEntity);
            train.addTrainEntities(wagonEntityList);
        }
        catch (Exception ex){
            ex.printStackTrace();
            e=ex;
        }
        finally {

            if (e != null) {
                Assert.fail(e.getMessage());
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

import cargoCompany.TrainDepartment;
import cargoCompany.randomPopulator.RandomUserPopulator;
import cargoCompany.utils.CargoCalculator;
import org.junit.*;
import train.Train;
import train.TrainTypeEnum;
import train.wagon.entity.User;
import train.wagon.entity.WagonEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RandomUserPopulatorTest {

    private RandomUserPopulator randomUserPopulator ;

    @BeforeClass
    public static void globalSetUp() {
        System.out.println("Initial setup...");
    }

    @Before
    public void setUp() {
        System.out.println("Code executes before each test method");
    }

    @Test
    public void testRandomUserPopulatorInstance()  {
        Exception e=null;
        randomUserPopulator = new RandomUserPopulator();
        try {
           randomUserPopulator.generateUsers(10,10);
           Assert.assertEquals(randomUserPopulator.fakeUsers.size(),100);
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
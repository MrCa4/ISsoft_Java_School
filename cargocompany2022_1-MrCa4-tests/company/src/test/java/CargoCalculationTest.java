
import cargoCompany.TrainDepartment;
import cargoCompany.utils.CargoCalculator;
import org.junit.*;
import train.Train;
import train.TrainTypeEnum;
import train.wagon.entity.Cargo;
import train.wagon.entity.User;
import train.wagon.entity.WagonEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class CargoCalculationTest {

    private CargoCalculator cargoCalculator ;

    @BeforeClass
    public static void globalSetUp() {
        System.out.println("Initial setup...");
    }

    @Before
    public void setUp() {
        System.out.println("Code executes before each test method");
    }

    @Test
    public void testCargoCalculateInstance() {
        Exception e=null;
        WagonEntity testCargo = new Cargo(50);
        List<WagonEntity> testWagonEntityList =
                new ArrayList<>();
        testWagonEntityList.add(testCargo);
        try {
            CargoCalculator.cargoCalculation(TrainTypeEnum.FREIGHT_STANDART,testWagonEntityList);
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
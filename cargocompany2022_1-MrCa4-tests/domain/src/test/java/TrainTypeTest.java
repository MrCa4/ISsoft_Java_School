
import org.junit.*;
import train.TrainTypeEnum;
import train.wagon.FreightWagon;
import train.wagon.PassengerWagon;

public class TrainTypeTest {

    private TrainTypeEnum trainTypeEnum ;

    @BeforeClass
    public static void globalSetUp() {
        System.out.println("Initial setup...");
    }

    @Before
    public void setUp() {
        System.out.println("Code executes before each test method");
    }

    @Test
    public void testTrainTypeInstance() {
        Assert.assertEquals(TrainTypeEnum.FREIGHT_STANDART.getWagon().getClass()
                ,TrainTypeEnum.FREIGHT_MAX.getWagon().getClass());
        Assert.assertEquals(TrainTypeEnum.FREIGHT_STANDART.getWagon().getClass(), FreightWagon.class.getClass());
        Assert.assertEquals(TrainTypeEnum.PASSENGER_PLACKART.getWagon().getClass()
                ,TrainTypeEnum.PASSENGER_KUPE.getWagon().getClass());
        Assert.assertEquals(TrainTypeEnum.PASSENGER_PLACKART.getWagon().getClass(), PassengerWagon.class.getClass());

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

import org.junit.*;
import train.TrainChain;
import train.wagon.PassengerWagon;


public class TrainChainTest {

    private TrainChain trainChain ;

    @BeforeClass
    public static void globalSetUp() {
        System.out.println("Initial setup...");
    }

    @Before
    public void setUp() {
        System.out.println("Code executes before each test method");
    }

    @Test
    public void testTrainChainInstance()  {
        trainChain = new TrainChain(new PassengerWagon());

        Assert.assertNull(trainChain.getNextWagon());
        Assert.assertNull(trainChain.getPreviousWagon());
        trainChain.setPreviousWagon(new PassengerWagon());
        trainChain.setNextWagon(new PassengerWagon());
        Assert.assertNotNull(trainChain.getNextWagon());
        Assert.assertNotNull(trainChain.getPreviousWagon());
        Assert.assertEquals(trainChain.getNextWagon().getClass(),new PassengerWagon().getClass());
        Assert.assertEquals(trainChain.getPreviousWagon().getClass(),new PassengerWagon().getClass());


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
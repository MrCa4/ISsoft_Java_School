
import cargoCompany.randomPopulator.RandomCargoPopulator;
import cargoCompany.randomPopulator.RandomUserPopulator;
import org.junit.*;

public class RandomCargoPopulatorTest {

    private RandomCargoPopulator randomCargoPopulator ;

    @BeforeClass
    public static void globalSetUp() {
        System.out.println("Initial setup...");
    }

    @Before
    public void setUp() {
        System.out.println("Code executes before each test method");
    }

    @Test
    public void testRandomCargoPopulatorInstance() {
        Exception e=null;
        randomCargoPopulator = new RandomCargoPopulator();
        try {
           randomCargoPopulator.generateCargo(100);
           Assert.assertEquals(randomCargoPopulator.fakeCargo.size(),100);
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
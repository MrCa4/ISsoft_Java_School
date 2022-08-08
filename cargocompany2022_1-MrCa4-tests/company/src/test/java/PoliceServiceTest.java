

import cargoCompany.Company;
import cargoCompany.securityDepartmentServices.PoliceService;
import org.junit.*;
import train.wagon.entity.User;


public class PoliceServiceTest {

    private PoliceService policeService;

    @BeforeClass
    public static void globalSetUp() {
        System.out.println("Initial setup...");
    }

    @Before
    public void setUp() {
        System.out.println("Code executes before each test method");
    }

    @Test
    public void testPoliceServiceInstance() {
        policeService = PoliceService.getPolicyService();
        Assert.assertEquals(policeService, PoliceService.getPolicyService());
    }

    @Test
    public void testPoliceServiceAssertedListInstance()  {
        policeService = PoliceService.getPolicyService();
        policeService.addCriminal(new User("testCriminal"
                ,"testCriminal"
                ,50
                ,false
                ,true));
        Assert.assertEquals(PoliceService.getArrestedCriminals().size(),1);
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
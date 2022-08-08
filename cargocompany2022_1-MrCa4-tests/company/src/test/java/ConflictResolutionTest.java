

import cargoCompany.securityDepartmentServices.ConflictResolution;
import cargoCompany.securityDepartmentServices.PoliceService;
import org.junit.*;


public class ConflictResolutionTest {

    private ConflictResolution conflictResolution ;

    @BeforeClass
    public static void globalSetUp() {
        System.out.println("Initial setup...");
    }

    @Before
    public void setUp() {
        System.out.println("Code executes before each test method");
    }

    @Test
    public void testConflictResolutionInstance() {
        conflictResolution = ConflictResolution.getConflictResolutionService();
        Assert.assertEquals(conflictResolution,ConflictResolution.getConflictResolutionService());

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
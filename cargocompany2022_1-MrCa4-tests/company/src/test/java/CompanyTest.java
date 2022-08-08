import cargoCompany.Company;
import cargoCompany.TrainDepartment;
import org.junit.*;

public class CompanyTest {

    private Company company ;

    @BeforeClass
    public static void globalSetUp() {
        System.out.println("Initial setup...");
    }

    @Before
    public void setUp() {
        System.out.println("Code executes before each test method");
    }

    @Test
    public void testCompanyInstance()  {
        company = Company.getCompany();
        Assert.assertEquals(company, Company.getCompany());

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
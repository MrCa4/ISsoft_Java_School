

import cargoCompany.Company;
import cargoCompany.securityDepartmentServices.DriverInspection;
import org.junit.*;
import train.wagon.entity.User;


public class DriverInspectionTest {



    @BeforeClass
    public static void globalSetUp() {
        System.out.println("Initial setup...");
    }

    @Before
    public void setUp() {
        System.out.println("Code executes before each test method");
    }

    @Test
    public void testDriverInspectionInstance(){
        Assert.assertFalse(DriverInspection.checkDriver(new User("test","test",10
                                                                    ,true,false)));
        Assert.assertFalse(DriverInspection.checkDriver(new User("test","test",10
                                                                    ,false,false)));
        Assert.assertTrue(DriverInspection.checkDriver(new User("test","test",20
                                                                    ,true,false)));
        Assert.assertFalse( DriverInspection.checkDriver(new User("test","test",20
                                                                    ,false,false)));

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
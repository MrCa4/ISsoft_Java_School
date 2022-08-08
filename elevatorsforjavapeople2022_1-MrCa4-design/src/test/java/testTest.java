import domain.elevator.Elevator;
import org.junit.*;

import java.lang.reflect.Method;


public class testTest {

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
//        Class<Object> TargetClass = Elevator;
//        Method method = TargetClass.getDeclaredMethod(methodName, argClasses);
//        method.setAccessible(true);
//        return method.invoke(targetObject, argObjects);
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

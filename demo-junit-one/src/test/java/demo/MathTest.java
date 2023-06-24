package demo;

import com.example.common.MathLib;
import org.junit.*;

public class MathTest {

    private MathLib mathLib;

    @BeforeClass
    public static void beforeClass() throws Exception{
        System.out.println("BeforeClass");
    }

    @AfterClass
    public static void afterClass() throws Exception{
        System.out.println("AfterClass");
    }

    @Before
    public void before() {
        System.out.println("Before");
        mathLib = new MathLib();
    }

    @After
    public void after() {
        System.out.println("After");
        mathLib = null;
    }

    @Test
    public void plus() {
        System.out.println("Test One");
        Assert.assertEquals(0, mathLib.plus(5, 5));
    }

    @Test
    public void subtraction() {
        System.out.println("Test One");
        Assert.assertEquals(0, mathLib.subtraction(5, 5));
    }
}

package testng;

import com.example.common.MathLib;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MathTestNG {

    @BeforeTest
    public void beforeTest() {
        System.out.println("beforeTest");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("afterTest");
    }

    @Test
    public void test() {
        MathLib mathLib = new MathLib();
        int result = mathLib.plus(5, 5);
        int expected = 100;
        Assert.assertEquals(result, expected);
    }
}

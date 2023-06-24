package parameterized;

import com.example.common.MathLib;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class ParameterizedTest {

    private int firstNumber;
    private int secondNumber;
    private int expectedResult;

    private MathLib mathLib;

    public ParameterizedTest(int firstNumber, int secondNumber, int expectedResult) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.expectedResult = expectedResult;
    }

    @Before
    public void init() {
        mathLib = new MathLib();
    }

    @Parameterized.Parameters
    public static Collection numbers() {
        return List.of(new Integer[][]{
                {4, 2, 2},
                {4, 1, 4},
                {4, 2, 4},
                {5, 5, 2},
                {6, 2, 2}
        });
    }

    @Test
    public void test() {
        System.out.printf("\n Parameterized: %d / %d = expected: %d",
                firstNumber, secondNumber, expectedResult);
        int result = mathLib.divide(firstNumber, secondNumber);
        Assert.assertEquals(expectedResult, result);
    }
}

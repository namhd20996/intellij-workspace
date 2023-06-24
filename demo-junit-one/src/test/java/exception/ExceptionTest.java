package exception;

import com.example.common.MathLib;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.fail;

public class ExceptionTest {

    private MathLib mathLib;

    @Before
    public void init() {
        mathLib = new MathLib();
    }

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void exceptionFour(){
        collector.addError(new Throwable("The first error:"));
        collector.addError(new Throwable("The second error:"));
        try {
            int result = mathLib.divide(5, 0);
        }catch (Exception e){
            collector.addError(e);
        }
        System.out.println("exceptionFour");
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void exceptionThree() {
        thrown.expect(ArithmeticException.class);
        thrown.expectMessage("Divide by zero");
        mathLib.divide(5, 0);
    }

    @Test(expected = ArithmeticException.class)
    public void expectionOne() {
        mathLib.divide(5, 0);
    }

    @Test
    public void exceptionTwo() {
        try {
            int result = mathLib.divide(5, 0);
            fail("Not found exception");
        } catch (Exception e) {
            Assert.assertThat(e, instanceOf(ArithmeticException.class));
            Assert.assertEquals("Divide by zero", e.getMessage());
        }
    }
}

package group;

import org.testng.annotations.Test;

public class GroupExampleTest {

    @Test(groups = "groupA")
    public void testOne() {
        System.out.println("testOne Group A");
    }

    @Test(groups = "groupA")
    public void testTwo() {
        System.out.println("testTwo Group A");
    }

    @Test(groups = "groupA")
    public void testThree() {
        System.out.println("testThree Group A");
    }

    @Test(groups = "groupB")
    public void testFour() {
        System.out.println("testFour Group A");
    }
}

package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.List;

public class SeleniumTest {

    @Test
    public void test() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("excludeSwitches",
                List.of("disable-popup-blocking", "enable-automation"));
        WebDriver driver = new ChromeDriver(chromeOptions);
        String url = "https://google.com";
        driver.manage().window().maximize();
        driver.get(url);
        driver.findElement(By.name("q")).sendKeys("MrBeast");
        WebElement element = driver.findElements(By.name("btnK")).get(1);
        Actions actions = new Actions(driver).click(element);
        actions.build().perform();
    }
}

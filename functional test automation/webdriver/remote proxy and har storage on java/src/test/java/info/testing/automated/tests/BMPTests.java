package info.testing.automated.tests;

import info.testing.automated.runner.BaseTest;
import info.testing.automated.runner.MonitorNetwork;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

/**
 * Author: Serhii Kuts
 * Date: 8/31/2014
 * Time: 4:50 PM
 */
public class BMPTests extends BaseTest {

    @MonitorNetwork
    @Test
    public void googleSearchAndCaptureTraffic() {
        getDriver().get("https://www.google.ru");
        getDriver().findElement(By.id("gbqfq"))
                .sendKeys("automated testing info" + Keys.ENTER);
    }
}

package info.testing.automated.tests;

import info.testing.automated.core.SamplePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.concurrent.TimeUnit;

/**
 * Author: Serhii Kuts
 */
public class DynamicLocatorsTests {

    @Test
    public void selectCategoryAndTopic() {
        WebDriver driver = null;
        SoftAssert softAssert = new SoftAssert();

        try {
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            driver.manage().window().maximize();

            driver.get("http://automated-testing.info");

            SamplePage samplePage = new SamplePage(driver)
                    .selectCategoryByName("webdriver")
                    .selectTopicByIndex(1);

            softAssert.assertEquals(samplePage.getCurrentCategory(), "webdriver", "Wrong category");
            softAssert.assertEquals(samplePage.getTopicCreator(), "val_ch", "Wrong topic starter");
            softAssert.assertAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}

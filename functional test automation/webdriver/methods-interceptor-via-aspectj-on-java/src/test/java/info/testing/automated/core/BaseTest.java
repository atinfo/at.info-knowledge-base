package info.testing.automated.core;

import info.testing.automated.abstraction.WebDriver;
import info.testing.automated.pages.SamplePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static info.testing.automated.core.Page.*;

/**
 * Author: Serhii Kuts
 */
public class BaseTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new WebDriver();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }

        Page.destroy();
    }

    public SamplePage samplePage() {
        return (SamplePage) getPage(SAMPLE, driver);
    }
}

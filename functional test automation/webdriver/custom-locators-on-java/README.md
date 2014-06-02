Java Selenium WebDriver example: how to create custom locators dynamically
======

Implemented via maven, testng.

Main usage: custom search logic implementation, e.g. for creating locators dynamically.

Inside of project you will see: 
 
 - Custom search annotation.
 - Custom search criteria enum.
 - BasePage implementation with several wrapped Selenium APIs.
 - Custom elements' initializer via reflection with nested PageObjects inheritance support.
 - Wrapper for By class, that implements dynamic locators' updater mechanism.
 - StringUtils with helper method for dynamic locators' updating.
 - SamplePage implementation with static and dynamic locators.
 - Simple test implementation with soft assertions mechanism. 


Test looks like the following:
```java
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
```

And locators look like this

```java
    @HTML(searchBy = XPATH, value = "//tbody/tr[?]/td[?]/a")
    private HTMLElement gridCells;

    @HTML(searchBy = CSS_SELECTOR, value = "li > .home")
    private HTMLElement linkAllCategories;

    @HTML(searchBy = XPATH, value = "//*[@class='category-dropdown-menu']//a[text()='?']")
    private HTMLElement linkCategoryInList;

    @HTML(searchBy = CSS_SELECTOR, value = "h1 > .badge-category")
    private HTMLElement linkBadgeCategory;

    @HTML(searchBy = CSS_SELECTOR, value = ".topic-creator > h3 > a")
    private HTMLElement linkTopicCreator;
```

Sikuli + webdriver examples with java programming
======

Implemented on maven, sikuli, testng, webdriver.

Inside of project you will see: 
 
 - created wrapper for basic actions
 - created image element interface
 - created basic test using webdriver + sikulix
 - created image element implementation
 - sikulix setup jar can be downloaded here: https://launchpad.net/sikuli/sikulix/1.1.0
 - after finishing with setting up sikulix, sikulix-api.jar from root folder should be installed into local maven repository before test execution
 - actions were implemented via observers - some kind of generic waits for images


Test look like this, so please have a look how it's implemented:
```java
package info.testing.automated.sikuli.examples;

import info.testing.automated.sikuli.core.Desktop;
import info.testing.automated.sikuli.element.ImageElement;
import info.testing.automated.sikuli.entities.IImageElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.script.Key;
import org.testng.annotations.Test;

/**
 * Author: Serhii Kuts
 */
public class CommonSikuliActionsTests {

    @Test
    public void sikuliClickAndType() {

        WebDriver driver = null;
        Desktop desktop = new Desktop();

        try {
            IImageElement inputSearch = new ImageElement(ClassLoader.getSystemResource("inputSearch.png").getPath(), 0.75f);
            IImageElement linkSite = new ImageElement(ClassLoader.getSystemResource("linkSite.png").getPath(), 0.75f);
            IImageElement linkAllCategories = new ImageElement(ClassLoader.getSystemResource("linkAllCategories.png").getPath(), 0.75f);
            IImageElement linkProgramming = new ImageElement(ClassLoader.getSystemResource("linkProgramming.png").getPath(), 0.75f);

            driver = new FirefoxDriver();
            driver.get("https://www.google.ru/");

            desktop.type(inputSearch, "automated-testing.into" + Key.ENTER, 2);
            desktop.clickAll(new IImageElement[] { linkSite, linkAllCategories, linkProgramming }, 20);

            Thread.sleep(3000);
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

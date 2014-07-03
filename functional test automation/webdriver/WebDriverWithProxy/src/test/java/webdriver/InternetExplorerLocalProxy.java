package webdriver;

import base.BaseTest;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Funker on 30.06.14.
 */
public class InternetExplorerLocalProxy extends BaseTest {

    @BeforeMethod
    public void setUpProxy() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, getProxy());
        //or
        //capabilities.setCapability(CapabilityType.PROXY, server.seleniumProxy());
        driver = new InternetExplorerDriver(capabilities);
    }

    @Test
    public void testName() throws Exception {
        doTest();
    }

}

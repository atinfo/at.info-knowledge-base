package remotewebdriver;

import base.BaseTest;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URL;

/**
 * Created by Funker on 30.06.14.
 */
public class ChromeRemote extends BaseTest {

    @BeforeMethod
    public void setUpProxy() throws Exception {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.PROXY, getProxy());
        //or
        //capabilities.setCapability(CapabilityType.PROXY, server.seleniumProxy());
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    }

    @Test
    public void testName() throws Exception {
        doTest();
    }
}

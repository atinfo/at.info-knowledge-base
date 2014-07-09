package atrernative;

import base.BaseTest;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Funker on 30.06.14.
 */
public class FireFoxProxyViaProfile extends BaseTest {

    @BeforeMethod
    public void setUpProxy() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.proxy.type", 1);
        profile.setPreference("network.proxy.http", proxyIp);
        profile.setPreference("network.proxy.http_port", port);

        capabilities.setCapability(FirefoxDriver.PROFILE, profile);

        driver = new FirefoxDriver(capabilities);
        //or
        //driver = new FirefoxDriver(profile);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testName() throws Exception {
        doTest();
    }
}

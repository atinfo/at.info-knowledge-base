package atrernative;

import base.BaseTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Funker on 30.06.14.
 */
public class ChromeProxyViaOptions extends BaseTest {

    @BeforeMethod
    public void setUpProxy() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type"); //hide --ignore-certifcate-errors https://code.google.com/p/chromedriver/issues/detail?id=799
        options.addArguments("--proxy-server=http://" + proxyIp + ":" + port);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        driver = new ChromeDriver(capabilities);
        //or
        //driver = new ChromeDriver(options);
    }

    @Test
    public void testName() throws Exception {
        doTest();
    }

}

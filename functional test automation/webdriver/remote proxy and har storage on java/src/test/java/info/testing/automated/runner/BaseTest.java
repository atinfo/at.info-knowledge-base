package info.testing.automated.runner;

import static info.testing.automated.proxy.BrowserMobProxy.*;

import info.testing.automated.proxy.BrowserMobProxy;
import info.testing.automated.proxy.HarStorage;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * Author: Serhii Kuts
 * Date: 8/31/2014
 * Time: 4:57 PM
 */
public class BaseTest {

    private static final String PROXY_IP = "127.0.0.1";
    private static final int PROXY_PORT = 9090;

    private static final String HAR_STORAGE_IP = "127.0.0.1";
    private static final String HAR_STORAGE_PORT = "5000";

    private static final HarStorage HAR_STORAGE = new HarStorage(HAR_STORAGE_IP, HAR_STORAGE_PORT);

    private WebDriver driver;
    private Proxy seleniumProxy;
    private BrowserMobProxy proxy;
    private String harDetailsLink;

    private static final Logger LOGGER = Logger.getLogger(BaseTest.class.getName());

    @BeforeMethod
    public void setUp(final ITestContext context, final Method method) {
        configureProxy(context, method);
        driver = new FirefoxDriver(getDesireCapabilities());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }

        try {
            LOGGER.info("Saving har into storage: " + HAR_STORAGE.save(proxy.getHarAsString()));
            LOGGER.info("Har link: " + harDetailsLink);
        } catch (Exception e) {
            LOGGER.severe("Can't save har into storage");
        } finally {
            if (proxy != null) {
                proxy.stop();
                proxy = null;
            }
        }
    }

    private void configureProxy(final ITestContext context, final Method method) {
        final MonitorNetwork monitorNetwork = method.getAnnotation(MonitorNetwork.class);

        if (monitorNetwork != null && monitorNetwork.enabled()) {
            final String initialPageID = context.getName() + " : " + method.getName();
            harDetailsLink = HAR_STORAGE.getHarDetailsURL(initialPageID);

            proxy = new BrowserMobProxy(PROXY_IP, PROXY_PORT);
            proxy.setSocketOperationTimeout(DEFAULT_SOCKET_TIMEOUT);
            proxy.setRequestTimeout(DEFAULT_REQUEST_TIMEOUT);

            // Getting port for Selenium proxy
            final int port = proxy.getPort();
            proxy.setPort(port);

            // Creating har on raised proxy for monitoring net statistics before first page is loaded.
            proxy.newHar(initialPageID, monitorNetwork.captureHeaders(), monitorNetwork.captureContent(),
                    monitorNetwork.captureBinaryContent());

            // Get the Selenium proxy object
            final String actualProxy = PROXY_IP + ":" + port;
            seleniumProxy = new Proxy();
            seleniumProxy.setHttpProxy(actualProxy).setFtpProxy(actualProxy)
                    .setSslProxy(actualProxy);
        }
    }

    private DesiredCapabilities getDesireCapabilities() {
        final DesiredCapabilities capability = new DesiredCapabilities();

        if (proxy != null && seleniumProxy != null) {
            capability.setCapability(CapabilityType.PROXY, seleniumProxy);
        } else {
            final Proxy noProxy = new Proxy();
            noProxy.setProxyType(Proxy.ProxyType.DIRECT);
            capability.setCapability(CapabilityType.PROXY, noProxy);
        }

        return capability;
    }

    public WebDriver getDriver() {
        return driver;
    }
}

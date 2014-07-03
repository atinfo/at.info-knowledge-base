package base;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

/**
 * Created by Funker on 30.06.14.
 */
public class BaseTest {

    public WebDriver driver;
    public ProxyServer server;
    public String proxyIp = "172.31.29.21";
    public int port = 8085;
    public String url = "http://automated-testing.info/";

    @BeforeTest
    public void startProxy() throws Exception {
        server = new ProxyServer(port);
        server.start();
        server.setCaptureHeaders(true);
        server.setCaptureContent(true);
        server.setLocalHost(InetAddress.getByName(proxyIp));
        server.newHar("test_proxy");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }

    @AfterTest
    public void stopProxy() throws Exception {
        Har har = server.getHar();
        server.stop();
        System.out.println("Size of har file is: " + har.getLog().getEntries().size());
    }

    public void doTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
        driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
        WebElement element = driver.findElement(By.xpath("//div[@id='search-dropdown']/input"));
        element.sendKeys("proxy");
        Thread.sleep(2000);
        element.sendKeys(Keys.ARROW_DOWN);
        element.sendKeys(Keys.ENTER);
    }

    public Proxy getProxy() {
        Proxy proxy = new Proxy();
        String PROXY = proxyIp + ":" + port;
        proxy.setProxyType(Proxy.ProxyType.MANUAL);
        proxy.setHttpProxy(PROXY);
        proxy.setSslProxy(PROXY);
        return proxy;
    }

}

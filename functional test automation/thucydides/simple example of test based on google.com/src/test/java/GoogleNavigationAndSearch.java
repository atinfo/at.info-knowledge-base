import net.thucydides.core.annotations.*;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.runners.ThucydidesRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

@Story(Application.Register.Booking.class)
@RunWith(ThucydidesRunner.class)
@Concurrent(threads="1")
public class GoogleNavigationAndSearch
{
    public Properties keyWords;
    {
        keyWords = PropertiesReader.readProperties("Google.properties");
    }

    @Managed(uniqueSession = true)
    private WebDriver driver;

    @Before
    public void myTest()  throws Exception
    {
        System.setProperty("webdriver.chrome.driver", "/home/rb/Downloads/chromedriver");
        driver.manage().window().maximize();
    }

    @After
    public void close()
    {
        driver.close();
    }

    @ManagedPages(defaultUrl = "http://google.com/")
    public Pages pages;

    @Steps
    public GoogleNavigation google;

   // @WithDriver("chrome")
    @WithTag("googleSearch")
    @Test
    public void GuestBooking() throws InterruptedException
    {
        google.start_browser();
        google.input_search_query
                (
                        keyWords.getProperty("searchQuery")
                );
        google.click_to_submit_search_query();
        Thread.sleep(15000);
    }
}

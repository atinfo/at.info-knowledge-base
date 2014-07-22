import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class FileUpload {

	static public void main(String[] args) {
		// Creating webdriver
		System.setProperty("webdriver.ie.driver", "C:\\WORK\\IEDriverServer_Win32_2.37.0\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();

	// Opening page. In this case - local HTML file.
    	driver.get("file://C:/WORK/test.html");

    	// Find element that uploads file.
    	WebElement fileInput = driver.findElement(By.id("file"));

    	// Set direct path to local file that needs to be uploaded. 
    	// That also can be a direct link to file in web, like - https://www.google.com.ua/images/srpr/logo11w.png
    	fileInput.sendKeys("file://C:/WORK/lenna.png");

    	// find button that sends form and click it.
    	driver.findElement(By.id("submit")).click();

    	// Closing driver and session
    	driver.quit();
	}
}

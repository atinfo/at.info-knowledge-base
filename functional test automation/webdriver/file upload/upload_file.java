import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class FileUpload {

	static public void main(String[] args) {
		// Создать экземпляр webdriver
		System.setProperty("webdriver.ie.driver", "C:\\WORK\\IEDriverServer_Win32_2.37.0\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();

	// Открыть страницу (в данном случае файл на локальной машине)
    	driver.get("file://C:/WORK/test.html");

    	// Найти на странице элемент для заливки файла
    	WebElement fileInput = driver.findElement(By.id("file"));

    	// Указать элементу путь до файла (на диске)
    	fileInput.sendKeys("file://C:/WORK/lenna.png");

    	// Найти на странице кнопку отправки формы и нажать её
    	driver.findElement(By.id("submit")).click();

    	// Закрыть браузер и сессию webdriver
    	driver.quit();
	}
}

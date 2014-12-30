package core.tests.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FeatureTests {

	// We take url from testng file <parameter name="url"
	// value="https://mail.google.com" />
	@Test
	@Parameters({ "url" })
	public void simpleTestLogInToGmail(String url) throws InterruptedException {
		// Run fire fox
		WebDriver driver = new FirefoxDriver();
		driver.get(url);
		driver.manage().window().maximize(); // maximize window
		Thread.sleep(5000);

		// Log in to gmail

		// Find object
		WebElement loginField = getElementById(driver, "Email");
		WebElement passwordField = getElementById(driver, "Passwd");
		WebElement signInButton = getElementById(driver, "signIn");

		// Fill in form and log in
		loginField.sendKeys("engage1.com@gmail.com");
		passwordField.sendKeys("Enkata2012");
		signInButton.click();
		Thread.sleep(5000);

		// delete all emails from inbox
		WebElement checkBoxAllEmails = getElementByXpath(
				driver,
				"//html/body/div[7]/div[3]/div/div[2]/div/div[2]/div/div/div/div/div/div/div/div/div/div/div/div/span/div");
		WebElement deleteButton = getElementByXpath(
				driver,
				"//html/body/div[7]/div[3]/div/div[2]/div/div[2]/div/div/div/div/div/div/div/div/div/div[2]/div[3]/div/div");

		checkBoxAllEmails.click();
		Thread.sleep(5000);
		deleteButton.click();

		Thread.sleep(5000);
		driver.quit();

	}

	private WebElement getElementById(WebDriver driver, String id) {

		return driver.findElement(By.id(id));
	}

	private WebElement getElementByXpath(WebDriver driver, String xpath) {

		return driver.findElement(By.xpath(xpath));
	}

	public static Boolean findEmailInTheList(WebDriver driver, String email) {
		for (int i = 1; i < 16; i++) {
			try {				
				String emailFind = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div/div[1]/div[2]/div[2]/div/table/tbody/tr[" + i + "]/td[3]")).getAttribute("textContent");
				if (emailFind.replace(" ", "").equals(email.replace(" ", "")))
					return true;

			} catch (Exception e) {

			}

		}
		return false;
	}
}

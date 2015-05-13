# Example on implementation screen recorder for web tests on webdriver by means of java.

Monte java library is used to implement it http://www.randelshofer.ch/monte/

Look inside how following methods are imlemented:

```java
public static void main(String[] argv) {
		// initialize web driver
		WebDriver driver = new FirefoxDriver();
		driver.get("http://automated-testing.info");

		// capture video
		initScreen();
		startVideoCapturing();
		stopVideoCapturing();

		// initialize web driver
		driver.quit();
	}
```

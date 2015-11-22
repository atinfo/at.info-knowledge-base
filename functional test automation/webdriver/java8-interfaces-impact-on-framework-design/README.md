Java 8 interfaces impact on test automation framework design.
======

This code provides an extensive samples of creating custom factories and code prettification using new Java 8 features.
You can find detailed instruction in the following articles:

 - http://qa-automation-notes.blogspot.com/2015/07/java-8-impact-on-test-automation.html
 - http://qa-automation-notes.blogspot.com/2015/08/java-8-impact-on-test-automation.html

Author: Serhii (Kuts) Korol (serhii.s.korol@gmail.com)

Prettified test case:
```java
@Test
public void correctLoginIntoGoogleAccount() {
	loadUrl("https://accounts.google.com")
		.setEmail("email")
		.clickNext()
		.setPassword("password")
		.staySignedIn(false)
		.signIn()
		.uploadFile("path");

	verifyTextEquals(homePage().getUsername(), "Sergey", "Username");
}    
```
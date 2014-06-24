#How to create simple test Using Java+JUnit+Thucydides

Goal is to show how to create simplest test automation scenario by means of Java, JUnit and Thucydides so newbie can try to experiment with code further. 


# Classes descriptions


1) GoogleNavigationAndSearch - Main.

2) GoogleNavigation - Steps.

3) Google - class to find elements.

4) Application - specify stories.

5) PropertiesReader - class to upload values from file (e.g. Google.properties).

6) Google.properties - file with test-data.
```
(e.g.)searchQuery = testing;
```

7) POM.


Google Seach simple test will look like this:
```java
google.start_browser(); // Start browser
google.input_search_query(keyWords.getProperty("searchQuery")); // Input search query 
google.click_to_submit_search_query(); // Click button to submit query
Thread.sleep(15000); // Wait to verify that that everything is working well
```

Related discussion on provided example you can find on http://automated-testing.info/t/thucydides-simple-test/4701

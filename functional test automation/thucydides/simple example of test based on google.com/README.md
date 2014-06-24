#How to create simple test Using Java+JUnit+Thucydides


1) GoogleNavigationAndSearch - Main
2) GoogleNavigation - Steps
3) Google - class to find elements
4) Application - specify stories
5) PropertiesReader - class to upload values from file (e.g. Google.properties)
6) Google.properties - file with test-data
(e.g.)searchQuery = testing
7) POM


Google Seach simple test:
```
Start browser - google.start_browser();
Input search query - google.input_search_query(keyWords.getProperty("searchQuery"));
Click button to submit query - google.click_to_submit_search_query();
Wait to verify that that everything is working well - Thread.sleep(15000);
```

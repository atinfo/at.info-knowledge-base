Selenium content supplier samples
======

This code provides couple of usage examples for Selenium content supplier library:
https://github.com/sskorol/selenium-supplier.

You can find detailed instruction in the following article:
http://qa-automation-notes.blogspot.com/2015/08/selenium-content-supplier.html

Author: Serhii (Kuts) Korol (serhii.s.korol@gmail.com)

You can easily download and unzip latest Selenium content the following way:
```java
    @Test
    public void downloadAndUnZipFilesLocally() {
        Stream.of(SeleniumContent.values())
                .parallel()
                .forEach(content -> localSupplier.downloadAndUnZipFile(content, OUTPUT_PATH));
    }  
```

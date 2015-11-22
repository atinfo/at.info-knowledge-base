package com.tools.qaa.interfaces;

import com.tools.qaa.entities.selenium.ChromeDriver;
import com.tools.qaa.entities.selenium.IEDriver;
import com.tools.qaa.entities.selenium.SeleniumServer;

public interface Content {

    enum SeleniumContent {
        IE_DRIVER_X32(new IEDriver(IE_DRIVER, OS.WIN32)),
        IE_DRIVER_X64(new IEDriver(IE_DRIVER, OS.WIN64)),
        CHROME_DRIVER_WIN32(new ChromeDriver(CHROME_DRIVER, OS.WIN32)),
        CHROME_DRIVER_MAC32(new ChromeDriver(CHROME_DRIVER, OS.MAC32)),
        CHROME_DRIVER_LINUX32(new ChromeDriver(CHROME_DRIVER, OS.LINUX32)),
        CHROME_DRIVER_LINUX64(new ChromeDriver(CHROME_DRIVER, OS.LINUX64)),
        SELENIUM_SERVER(new SeleniumServer(SELENIUM_SERVER_STANDALONE));

        private Content content;

        SeleniumContent(Content content) {
            this.content = content;
        }

        public Content getContent() {
            return content;
        }

        public String toString() {
            return content.getName();
        }
    }

    enum OS {
        WIN32("Win32"),
        WIN64("x64"),
        LINUX32("linux32"),
        LINUX64("linux64"),
        MAC32("mac32");

        private String name;

        OS(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    String IE_DRIVER = "IEDriverServer";
    String CHROME_DRIVER = "chromedriver";
    String SELENIUM_SERVER_STANDALONE = "selenium-server-standalone";

    default String getUrl() {
        return "http://selenium-release.storage.googleapis.com";
    }

    String getName();
}

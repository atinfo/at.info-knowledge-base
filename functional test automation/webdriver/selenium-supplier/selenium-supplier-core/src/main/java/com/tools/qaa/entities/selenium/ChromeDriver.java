package com.tools.qaa.entities.selenium;

import com.tools.qaa.interfaces.Content;

public class ChromeDriver implements Content {

    private String name;
    private OS osType;

    public ChromeDriver(final String name, final OS osType) {
        this.name = name;
        this.osType = osType;
    }

    public String getUrl() {
        return "http://chromedriver.storage.googleapis.com";
    }

    public String getName() {
        return name + "_" + osType.getName().toLowerCase();
    }
}

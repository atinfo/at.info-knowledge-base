package com.blogspot.notes.automation.qa.entities;

/**
 * Created by Serhii Kuts
 */
public enum CommonTask {
    FIREFOX("firefox"),
    CHROME("chrome"),
    IE_BROWSER("iexplore"),
    IE_DRIVER("IEDriver"),
    HAR_STORAGE("python");

    private String name;

    private CommonTask(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

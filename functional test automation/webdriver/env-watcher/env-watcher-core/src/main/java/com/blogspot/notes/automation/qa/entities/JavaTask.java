package com.blogspot.notes.automation.qa.entities;

/**
 * Created by Serhii Kuts
 */
public enum JavaTask {
    SELENIUM("selenium"),
    SIKULI("sikuli"),
    BROWSER_MOB_PROXY("proxy");

    private String name;

    private JavaTask(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

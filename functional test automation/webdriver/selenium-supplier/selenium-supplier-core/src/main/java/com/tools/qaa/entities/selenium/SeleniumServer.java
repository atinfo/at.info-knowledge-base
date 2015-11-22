package com.tools.qaa.entities.selenium;

import com.tools.qaa.interfaces.Content;

public class SeleniumServer implements Content {

    private String name;

    public SeleniumServer(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

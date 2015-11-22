package com.tools.qaa.entities.selenium;

import com.tools.qaa.interfaces.Content;

public class IEDriver implements Content {

    private String name;
    private OS osName;

    public IEDriver(final String name, final OS osName) {
        this.name = name;
        this.osName = osName;
    }

    public String getName() {
        return name + "_" + osName.getName();
    }
}

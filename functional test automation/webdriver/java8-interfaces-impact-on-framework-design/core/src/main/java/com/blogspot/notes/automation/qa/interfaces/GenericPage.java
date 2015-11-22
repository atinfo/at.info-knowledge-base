package com.blogspot.notes.automation.qa.interfaces;

import com.blogspot.notes.automation.qa.wrappers.BasePage;

import static com.blogspot.notes.automation.qa.wrappers.BaseTest.*;

public interface GenericPage {

    static BasePage getPageObject(final GenericPage page) {
        getPages().putIfAbsent(page, page.create());
        return getPages().get(page);
    }

    static void navigateTo(final String url) {
        try {
            getWebDriver().navigate().to(url);
        } catch (Exception e) {
            throw new AssertionError("Unable to access the following URL: " + url, e);
        }
    }

    BasePage create();
}

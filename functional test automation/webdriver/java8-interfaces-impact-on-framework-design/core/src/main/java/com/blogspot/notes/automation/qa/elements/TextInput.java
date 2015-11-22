package com.blogspot.notes.automation.qa.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TextInput extends HTMLElement {

    public TextInput(final WebDriver driver, final SearchBy elementSearchCriteria, final String elementValue) {
        super(driver, elementSearchCriteria, elementValue);
    }

    public void type(final CharSequence text) {
        type(text, true);
    }

    public void clearInput() {
        waitUntil(ExpectedConditions::elementToBeClickable).clear();
    }

    public void type(final CharSequence text, final boolean clear) {
        if (clear) {
            clearInput();
        }
        waitUntil(ExpectedConditions::elementToBeClickable).sendKeys(text);
    }
}

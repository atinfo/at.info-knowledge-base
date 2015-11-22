package com.blogspot.notes.automation.qa.interfaces;

import ru.yandex.qatools.allure.annotations.Step;

import static org.testng.Assert.assertEquals;

public interface Validator {

    @Step("Verify that \"{2}\" = \"{1}\".")
    default void verifyTextEquals(final String actual, final String expected, final String message) {
        assertEquals(actual, expected, message);
    }
}


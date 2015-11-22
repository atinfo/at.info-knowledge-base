package com.blogspot.notes.automation.qa.tests;

import com.blogspot.notes.automation.qa.interfaces.ScreenElement;
import com.blogspot.notes.automation.qa.interfaces.TestCase;
import com.blogspot.notes.automation.qa.wrappers.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthorizationTests extends BaseTest implements TestCase {

    @BeforeMethod
    void mockSikuliElements() {
        ScreenElement input = mock(ScreenElement.class);
        ScreenElement button = mock(ScreenElement.class);
        when(getSikuliDriver().findElement("inputFilePath.png", 0.8f)).thenReturn(input);
        when(getSikuliDriver().findElement("buttonUpload.png", 0.8f)).thenReturn(button);
        doNothing().when(input).type("path");
        doNothing().when(button).click();
    }

    @Test
    public void correctLoginIntoGoogleAccount() {
        loadUrl("https://accounts.google.com")
                .setEmail("email")
                .clickNext()
                .setPassword("password")
                .staySignedIn(false)
                .signIn()
                .uploadFile("path");

        verifyTextEquals(homePage().getUsername(), "Sergey", "Username");
    }
}

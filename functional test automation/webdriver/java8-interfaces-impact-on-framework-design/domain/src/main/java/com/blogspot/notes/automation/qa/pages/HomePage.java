package com.blogspot.notes.automation.qa.pages;

import com.blogspot.notes.automation.qa.annotations.HTML;
import com.blogspot.notes.automation.qa.annotations.Image;
import com.blogspot.notes.automation.qa.elements.ImageElement;
import com.blogspot.notes.automation.qa.elements.Label;
import com.blogspot.notes.automation.qa.wrappers.BasePage;
import ru.yandex.qatools.allure.annotations.Step;

import static com.blogspot.notes.automation.qa.elements.HTMLElement.SearchBy.*;

public class HomePage extends BasePage {

    @HTML(searchBy = CSS_SELECTOR, value = ".gb_8a.gb_r")
    private Label labelUsername;

    @Image(name = "inputFilePath.png", similarity = 0.8f)
    private ImageElement inputFilePath;

    @Image(name = "buttonUpload.png", similarity = 0.8f)
    private ImageElement buttonUpload;

    @Step("Upload a file \"{0}\".")
    public HomePage uploadFile(final String path) {
        inputFilePath.type(path);
        buttonUpload.click();
        return this;
    }

    public String getUsername() {
        return labelUsername.getText();
    }
}

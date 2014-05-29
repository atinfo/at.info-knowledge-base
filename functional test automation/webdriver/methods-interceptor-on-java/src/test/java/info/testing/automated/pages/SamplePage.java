package info.testing.automated.pages;

import info.testing.automated.annotations.Publish;
import info.testing.automated.core.BasePage;
import info.testing.automated.core.WebDriver;

/**
 * Author: Serhii Kuts
 */
public class SamplePage extends BasePage {

    public SamplePage(WebDriver driver) {
        super(driver);
    }

    @Publish
    public SamplePage firstAction() {
        return this;
    }

    public SamplePage secondAction() {
        return this;
    }

    @Publish
    public SamplePage thirdAction() {
        return this;
    }

    public SamplePage fourthAction() {
        return this;
    }
}

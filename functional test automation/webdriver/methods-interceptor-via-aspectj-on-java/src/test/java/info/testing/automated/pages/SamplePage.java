package info.testing.automated.pages;

import info.testing.automated.annotations.Publish;
import info.testing.automated.abstraction.BasePage;
import info.testing.automated.abstraction.WebDriver;

/**
 * Author: Serhii Kuts
 */
public class SamplePage extends BasePage {

    public SamplePage(final WebDriver driver) {
        super(driver);
    }

    @Publish
    public SamplePage firstAction(final String arg) {
        return this;
    }

    public SamplePage secondAction() {
        return this;
    }

    @Publish
    public SamplePage thirdAction(final String arg) {
        return this;
    }

    @Publish
    public SamplePage fourthAction() {
        return this;
    }
}

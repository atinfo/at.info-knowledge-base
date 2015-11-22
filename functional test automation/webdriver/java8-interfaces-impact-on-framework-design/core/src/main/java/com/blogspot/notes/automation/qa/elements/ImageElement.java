package com.blogspot.notes.automation.qa.elements;

import com.blogspot.notes.automation.qa.interfaces.ScreenElement;
import com.blogspot.notes.automation.qa.interfaces.SikuliDriver;

import java.util.logging.Logger;

public class ImageElement implements ScreenElement {

    private static final Logger LOGGER = Logger.getLogger(ImageElement.class.getName());

    private String path;
    private float similarity;
    private SikuliDriver driver;

    public ImageElement(final SikuliDriver driver, final String path, final float similarity) {
        this.driver = driver;
        this.path = path;
        this.similarity = similarity;
    }

    public ScreenElement findElement() {
        return driver.findElement(path, similarity);
    }

    public void click() {
        LOGGER.info("Clicking " + toString());
        findElement().click();
    }

    public void type(final String text) {
        LOGGER.info("Typing '" + text + "' into " + toString());
        findElement().type(text);
    }

    public String toString() {
        return "ImageElement{" +
                "path='" + path + '\'' +
                ", similarity=" + similarity +
                '}';
    }
}

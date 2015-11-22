package com.blogspot.notes.automation.qa.wrappers;

import com.blogspot.notes.automation.qa.annotations.HTML;
import com.blogspot.notes.automation.qa.annotations.Image;
import com.blogspot.notes.automation.qa.interfaces.ElementsSupplier;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

public abstract class BasePage implements ElementsSupplier {

    public BasePage() {
        initElements(this);
    }

    @Override
    public Stream<?> getSupportedDrivers() {
        return Stream.of(BaseTest.getWebDriver(), BaseTest.getSikuliDriver());
    }

    @Override
    public Stream<Class<? extends Annotation>> getSupportedAnnotationTypes() {
        return Stream.of(HTML.class, Image.class);
    }
}

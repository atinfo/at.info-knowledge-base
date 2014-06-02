package info.testing.automated.core;

import info.testing.automated.annotations.HTML;
import info.testing.automated.utils.elements.HTMLElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Author: Serhii Kuts
 */
public class BasePage {

    private WebDriver driver;

    public BasePage(final WebDriver driver) {

        this.driver = driver;
        initElements();
    }

    public final void initElements() {

        final List<Field> fields = new ArrayList<>();
        Class currentPageObject = this.getClass();

        while (currentPageObject != BasePage.class) {
            fields.addAll(new ArrayList<>(Arrays.asList(currentPageObject.getDeclaredFields())));
            currentPageObject = currentPageObject.getSuperclass();
        }

        for (Field field : fields) {
            final HTML fieldAnnotation = field.getAnnotation(HTML.class);
            final boolean accessible = field.isAccessible();

            if (fieldAnnotation != null) {
                try {
                    field.setAccessible(true);
                    field.set(this, new HTMLElement(fieldAnnotation.searchBy(), fieldAnnotation.value()));
                    field.setAccessible(accessible);
                } catch (IllegalAccessException e) {
                    // Log or throw your exception here
                }
            }
        }
    }

    public HTMLElement updateElement(final HTMLElement element, final String... values) {
        return element.updateElement(values);
    }

    public WebElement findElement(final HTMLElement element) {
        return driver.findElement(element.getLocator());
    }

    public void click(final HTMLElement element) {
        findElement(element).click();
    }

    public String getText(final HTMLElement element) {
        return findElement(element).getText();
    }
}

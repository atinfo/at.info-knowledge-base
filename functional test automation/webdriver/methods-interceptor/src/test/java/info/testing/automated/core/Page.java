package info.testing.automated.core;

import info.testing.automated.pages.SamplePage;
import info.testing.automated.utils.ReflectionUtils;

import java.util.HashMap;

/**
 * Author: Serhii Kuts
 */
public enum Page {

    SAMPLE {
        BasePage create(final WebDriver driver) {
            return ReflectionUtils.create(SamplePage.class, driver);
        }
    };

    abstract BasePage create(final WebDriver driver);

    private static final ThreadLocal<HashMap<Page, BasePage>> PAGES = new ThreadLocal<>();

    public static BasePage getPage(final Page page, final WebDriver driver) {
        if (PAGES.get() == null) {
            PAGES.set(new HashMap<Page, BasePage>());
        }

        if (PAGES.get().get(page) == null) {
            for (Page currentPage : Page.values()) {
                if (currentPage.equals(page)) {
                    PAGES.get().put(currentPage, currentPage.create(driver));
                    break;
                }
            }
        }

        return PAGES.get().get(page);
    }

    public static void destroy() {
        if (PAGES.get() != null) {
            PAGES.remove();
        }

        ReflectionUtils.clearMethodsCallsList();
    }
}

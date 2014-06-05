package info.testing.automated.core;

import info.testing.automated.annotations.HTML;
import static info.testing.automated.enums.SearchBy.*;
import info.testing.automated.utils.elements.HTMLElement;
import org.openqa.selenium.WebDriver;

/**
 * Author: Serhii Kuts
 */
public class SamplePage extends BasePage {

    @HTML(searchBy = XPATH, value = "//tbody/tr[?]/td[?]/a")
    private HTMLElement gridCells;

    @HTML(searchBy = CSS_SELECTOR, value = "li > .home")
    private HTMLElement linkAllCategories;

    @HTML(searchBy = XPATH, value = "//*[@class='category-dropdown-menu']//a[text()='?']")
    private HTMLElement linkCategoryInList;

    @HTML(searchBy = CSS_SELECTOR, value = "h1 > .badge-category")
    private HTMLElement linkBadgeCategory;

    @HTML(searchBy = CSS_SELECTOR, value = ".topic-creator > h3 > a")
    private HTMLElement linkTopicCreator;

    public SamplePage(final WebDriver driver) {
        super(driver);
    }

    public SamplePage selectCategoryByName(final String categoryName) {
        click(linkAllCategories);
        click(updateElement(linkCategoryInList, categoryName));
        return this;
    }

    public SamplePage selectTopicByIndex(final int row) {
        click(updateElement(gridCells, String.valueOf(row), "1"));
        return this;
    }

    public String getCurrentCategory() {
        return getText(linkBadgeCategory);
    }

    public String getTopicCreator() {
        return getText(linkTopicCreator);
    }
}

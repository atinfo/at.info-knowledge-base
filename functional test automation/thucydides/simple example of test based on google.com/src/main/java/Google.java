import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Google extends PageObject
{
    private static final Integer CATALOG_WAIT_FOR_TIMEOUT = 20000;

    public Google(WebDriver driver) {
        super(driver, CATALOG_WAIT_FOR_TIMEOUT);
    }

    @FindBy(name = "q")
    private WebElement inputField;

    public void setInputField(String searchQuery)
    {
        element(inputField).type(searchQuery);
    }

    @FindBy(id = "gbqfb")
    private WebElement submitSearch;

    public void setSubmitSearch() throws InterruptedException
    {
        element(submitSearch).click();
    }
}

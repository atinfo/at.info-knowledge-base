package com.tools.qaa.elements;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Author: Sergey Korol.
 */
public class HTMLElement extends By {

	public enum SearchBy {
		ID,
		XPATH,
		LINK_TEXT,
		CLASS_NAME,
		CSS_SELECTOR,
		TAG_NAME,
		NAME,
		PARTIAL_LINK_TEXT
	}

	private By locator;
	private WebDriverWait wait;
	private WebDriver driver;

	private String elementValue;
	private SearchBy elementSearchCriteria;

	private static final long DEFAULT_TIMEOUT = 5;

	public HTMLElement(final WebDriver driver, final SearchBy elementSearchCriteria, final String elementValue) {
		this.elementSearchCriteria = elementSearchCriteria;
		this.elementValue = elementValue;
		this.driver = driver;

		if (this.driver != null) {
			this.wait = new WebDriverWait(this.driver, DEFAULT_TIMEOUT);
		}

		if (getElementSearchCriteria() != null && getElementValue() != null) {
			initElement(getElementSearchCriteria(), getElementValue());
		}
	}

	public By getLocator() {
		return locator;
	}

	public String getElementValue() {
		return elementValue;
	}

	public SearchBy getElementSearchCriteria() {
		return elementSearchCriteria;
	}

	public Object executeJS(final String script) {
		return ((JavascriptExecutor) driver).executeScript(script);
	}

	public WebElement waitUntil(final Function<By, ExpectedCondition<WebElement>> condition) {
		return waitUntil(condition, Optional.<Long>empty());
	}

	public WebElement waitUntil(final Function<By, ExpectedCondition<WebElement>> condition, final Optional<Long> timeout) {
		try {
			return getWait(timeout).until(condition.apply(getLocator()));
		} catch (Exception e) {
			throw new AssertionError("Unable to find element by " + getElementSearchCriteria() + " = \"" + getElementValue() + "\"", e);
		}
	}

	private FluentWait<WebDriver> getWait(final Optional<Long> timeout) {
		return timeout.map(sec -> wait.withTimeout(sec, TimeUnit.SECONDS))
				.orElse(wait.withTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS));
	}

	private void initElement(final SearchBy searchBy, final String searchString) {
		switch (searchBy) {
			case ID:
				locator = By.id(searchString);
				break;
			case CSS_SELECTOR:
				locator = By.cssSelector(searchString);
				break;
			case CLASS_NAME:
				locator = By.className(searchString);
				break;
			case XPATH:
				locator = By.xpath(searchString);
				break;
			case LINK_TEXT:
				locator = By.linkText(searchString);
				break;
			case TAG_NAME:
				locator = By.tagName(searchString);
				break;
			case PARTIAL_LINK_TEXT:
				locator = By.partialLinkText(searchString);
				break;
			case NAME:
				locator = By.name(searchString);
				break;
		}
	}

	@Override
	public List<WebElement> findElements(final SearchContext searchContext) {
		return new ArrayList<>();
	}
}

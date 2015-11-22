package com.tools.qaa.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Author: Sergey Korol.
 */
public class Select2 extends HTMLElement {

	public Select2(final WebDriver driver, final SearchBy elementSearchCriteria, final String elementValue) {
		super(driver, elementSearchCriteria, elementValue);
	}

	public void selectByVisibleText(final String text) {
		waitUntil(ExpectedConditions::visibilityOfElementLocated);
		executeJS("var value = $(\"" + getElementValue() + "+select\").find('option:contains(\"" + text + "\")').val();" +
				"$(\"" + getElementValue() + "\").select2(\"val\", value);");
	}

	public String getSelectedText() {
		waitUntil(ExpectedConditions::visibilityOfElementLocated);
		return (String) executeJS("return $(\"" + getElementValue() + "\").select2('data').text;");
	}
}

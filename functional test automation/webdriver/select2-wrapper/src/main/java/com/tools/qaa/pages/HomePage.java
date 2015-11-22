package com.tools.qaa.pages;

import com.tools.qaa.core.BasePage;
import com.tools.qaa.annotations.HTML;
import com.tools.qaa.elements.Select2;

import static com.tools.qaa.elements.HTMLElement.SearchBy.*;

/**
 * Author: Sergey Korol.
 */
public class HomePage extends BasePage {

	@HTML(searchBy = CSS_SELECTOR, value = "div[id*=listBox1]")
	private Select2 listBoxWeekDay;

	public HomePage selectWeekDay(final String day) {
		listBoxWeekDay.selectByVisibleText(day);
		return this;
	}

	public String getSelectedWeekDay() {
		return listBoxWeekDay.getSelectedText();
	}
}

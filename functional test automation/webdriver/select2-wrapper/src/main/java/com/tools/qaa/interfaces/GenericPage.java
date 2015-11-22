package com.tools.qaa.interfaces;

import com.tools.qaa.core.BasePage;

import static com.tools.qaa.core.BaseTest.getPages;

/**
 * Author: Sergey Korol.
 */
public interface GenericPage {

	static BasePage getPageObject(final GenericPage page) {
		getPages().putIfAbsent(page, page.create());
		return getPages().get(page);
	}

	BasePage create();
}

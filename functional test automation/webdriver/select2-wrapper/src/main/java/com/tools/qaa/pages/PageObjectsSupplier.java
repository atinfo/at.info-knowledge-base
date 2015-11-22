package com.tools.qaa.pages;

import com.tools.qaa.core.BasePage;
import com.tools.qaa.interfaces.GenericPage;

import static com.codeborne.selenide.Selenide.open;
import static com.tools.qaa.interfaces.GenericPage.getPageObject;

/**
 * Author: Sergey Korol.
 */
public interface PageObjectsSupplier {

	enum PageObject implements GenericPage {
		HOME {
			public BasePage create() {
				return new HomePage();
			}
		}
	}

	default HomePage loadUrl(final String url) {
		open(url);
		return homePage();
	}

	default HomePage homePage() {
		return (HomePage) getPageObject(PageObject.HOME);
	}
}

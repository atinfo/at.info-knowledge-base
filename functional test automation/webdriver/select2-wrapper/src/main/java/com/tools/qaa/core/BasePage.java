package com.tools.qaa.core;

import com.tools.qaa.interfaces.ElementsSupplier;

import java.util.stream.Stream;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Author: Sergey Korol.
 */
public class BasePage implements ElementsSupplier {

	public BasePage() {
		initElements(this);
	}

	@Override
	public Stream<?> getSupportedDrivers() {
		return Stream.of(getWebDriver());
	}
}

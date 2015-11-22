package com.tools.qaa.core;

import com.tools.qaa.interfaces.GenericPage;
import org.testng.annotations.AfterMethod;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.collections.MapUtils.isEmpty;

/**
 * Author: Sergey Korol.
 */
public class BaseTest {

	private static final ThreadLocal<Map<GenericPage, BasePage>> PAGES =
			new ThreadLocal<Map<GenericPage, BasePage>>() {
				public Map<GenericPage, BasePage> initialValue() {
					return new HashMap<>();
				}
			};

	public static Map<GenericPage, BasePage> getPages() {
		return PAGES.get();
	}

	private void cleanUpPages() {
		if (!isEmpty(getPages())) {
			PAGES.remove();
		}
	}

	@AfterMethod
	public void tearDown() {
		cleanUpPages();
	}
}

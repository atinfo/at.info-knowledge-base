package com.tools.qaa.tests;

import com.tools.qaa.core.BaseTest;
import com.tools.qaa.pages.PageObjectsSupplier;
import org.junit.Assert;
import org.testng.annotations.*;

/**
 * Author: Sergey Korol.
 */
public class Select2Tests extends BaseTest implements PageObjectsSupplier {

	@Test
	public void selectValidWeekDay() {
		loadUrl("http://www.bootstrap4xpages.com/bs4xp/demos.nsf/Bootstrap_Select2Listbox.xsp")
				.selectWeekDay("Monday");

		Assert.assertEquals("Monday", homePage().getSelectedWeekDay());
	}
}

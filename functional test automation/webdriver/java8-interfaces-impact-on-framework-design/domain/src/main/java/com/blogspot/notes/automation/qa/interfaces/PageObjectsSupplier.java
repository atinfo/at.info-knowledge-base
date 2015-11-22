package com.blogspot.notes.automation.qa.interfaces;

import com.blogspot.notes.automation.qa.pages.HomePage;
import com.blogspot.notes.automation.qa.pages.LoginPage;
import com.blogspot.notes.automation.qa.wrappers.BasePage;
import ru.yandex.qatools.allure.annotations.Step;

import static com.blogspot.notes.automation.qa.interfaces.GenericPage.*;
import static com.blogspot.notes.automation.qa.interfaces.PageObjectsSupplier.PageObject.*;

public interface PageObjectsSupplier {

    enum PageObject implements GenericPage {
        LOGIN {
            public BasePage create() {
                return new LoginPage();
            }
        },
        HOME {
            public BasePage create() {
                return new HomePage();
            }
        }
    }

    @Step("Open browser and type the following URL: {0}")
    default LoginPage loadUrl(final String url) {
        navigateTo(url);
        return loginPage();
    }

    default HomePage homePage() {
        return (HomePage) getPageObject(HOME);
    }

    default LoginPage loginPage() {
        return (LoginPage) getPageObject(LOGIN);
    }
}

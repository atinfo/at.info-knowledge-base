# How to create typed webelement on python webdriver

When you create your web test automation with python and webdriver there is not typed webelement support out of the box. How it can be implemented?

*Also link provided on our github page http://atinfo.github.io/at.info-knowledge-base/

# Solution

Implemented with webdriver and python

One of the solution you can use it's just to create custom weblement class that will inherit from `from selenium.webdriver.remote.webelement import WebElement`. And then you can add some additional methods you need up to your purpose.

For example how to create Link type element with some special method:

```
from selenium.webdriver import Firefox
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webelement import WebElement

class Link(WebElement):
    def get_href(self):
        return self.get_attribute('href')

driver = Firefox()
driver.get('http://ya.ru')
element = driver.find_element(By.XPATH, 'id("mail")/a')
element.__class__ = Link
print element.get_href()
driver.close()
```

Related discussions can be found on http://automated-testing.info/t/code-recipe-kak-bystro-i-prosto-sozdat-kastomnye-tipizirovannye-elementy-dlya-vashih-webdriver-testov-na-python/4665

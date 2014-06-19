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
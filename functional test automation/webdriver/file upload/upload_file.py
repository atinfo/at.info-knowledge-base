# -*- coding: utf-8 -*-

# подключаем необходимые модули
from selenium import webdriver
import os

# создаём объект WebDriver для браузера chrome
driver = webdriver.Chrome()

# открываем страницу с формой загрузки файла
driver.get("file://" + os.getcwd() + "/index.html")

# находим элемент <input type="file">
element = driver.find_element_by_id("file")

# заполняем элемент путём до загружаемого файла
element.send_keys(os.getcwd() + "/lenna.png")

# находим элемент <input type="file">
element = driver.find_element_by_id("submit")

# нажимаем на элемент (отправляем форму)
element.click()

# закрываем браузер
driver.quit()
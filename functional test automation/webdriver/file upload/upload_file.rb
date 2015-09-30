# encoding: utf-8

# подключам необходимый gem
require 'selenium-webdriver'

# создаём объект WebDriver для браузера chrome
driver = Selenium::WebDriver.for :chrome

# открываем страницу с формой загрузки файла
driver.get "file://#{File.expand_path(File.dirname(__FILE__))}/index.html"

# находим элемент <input type="file">
element = driver.find_element :id, 'file'

# заполняем элемент путём до загружаемого файла
element.send_keys "#{File.expand_path(File.dirname(__FILE__))}/lenna.png"

# находим элемент <input type="file">
element = driver.find_element :id, 'submit'

# нажимаем на элемент (отправляем форму)
element.click

# закрываем браузер
driver.close

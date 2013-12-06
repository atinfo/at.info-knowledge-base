from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import Select
from selenium.webdriver.support.ui import WebDriverWait
from selenium.common.exceptions import NoSuchElementException,
ElementNotVisibleException
from browsermobproxy import Server
import urlparse

server = Server(r"c:\browsermob\bin\browsermob-proxy.bat")
server.start()
proxy = server.create_proxy()
proxy.new_har()

chrome_options = webdriver.ChromeOptions()
proxy = urlparse.urlparse(proxy.proxy).netloc
chrome_options.add_argument('--proxy-server=%s' % proxy)
driver = webdriver.Chrome(
    executable_path=r"c:\chromedriver.exe",
    chrome_options=chrome_options)
driver.get("http://google.com.ua/")
driver.find_element_by_id("gbqfsb").click()

print proxy.har

driver.quit()
server.stop()

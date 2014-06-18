from selenium import selenium

class SeleniumWrapper(object):
    """
    Singleton reference to the config information
    """
    _instance = None
    def __new__(cls, *args, **kwargs):
        if not cls._instance:
            cls._instance = super(SeleniumWrapper, cls).__new__(cls, *args, **kwargs)
        return cls._instance

    def connect(self, host, port, browser, server):
        self.connection = selenium(host, port, browser, server)
        return self.connection
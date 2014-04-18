import pytest
import logging
from multiprocessing.process import current_process
from threading import current_thread
import time

logging.basicConfig(filename="log.txt", filemode="w")
log = logging.getLogger()
log.setLevel(logging.DEBUG)

handler = logging.StreamHandler()
handler.setLevel(logging.DEBUG)
formatter = logging.Formatter("%(levelname)s - %(message)s")
handler.setFormatter(formatter)
log.addHandler(handler)


def pytest_configure(config):
    print("pytest_configure")
    logging.info("pytest_configure")
    # d("configure")
    # if not hasattr(config, 'slaveinput'):
    #      d("slave input")

def pytest_sessionstart(session):
    logging.info("pytest_sessionstart")
    print("pytest_sessionstart")
    # d("session start")

def pytest_runtest_setup(item):
    # called for running each test in 'a' directory
    print ("setting up", item)

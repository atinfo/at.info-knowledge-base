# Try with:
# nosetests --processes 5 test_debug_parallel.py  -- setUpClass called once per suite,
#                                                    same behaviour as SHARE=False or SPLIT=False
# SHARE=True nosetests --processes 5 test_debug_parallel.py  -- setUpClass called once for all inheritors
# SPLIT=True nosetests --processes 5 test_debug_parallel.py  -- many-many setUpClass
# py.test -s -n 5 test_parallel.py  -- same as nose _multiprocess_can_split_=True

# Requirements:
# python_2.7
# py.test
# xdist
# nose

import os

import time
import logging
from threading import current_thread
from multiprocessing.process import current_process

import testtools


log = logging.getLogger()
log.setLevel(logging.DEBUG)

handler = logging.StreamHandler()
handler.setLevel(logging.DEBUG)
formatter = logging.Formatter("%(levelname)s - %(message)s")
handler.setFormatter(formatter)
log.addHandler(handler)


def d(s):
    log.debug("{0} {1} {2} {3}".format(current_process().ident, current_thread().ident, time.time(), s))


i = 0  # per process counter
quant = 0.03  # increase if too fast


class FirstTest(testtools.TestCase):
    if 'SPLIT' in os.environ:
        can_spilt = os.getenv('SPLIT').lower() in ['true']
        _multiprocess_can_split_ = can_spilt
        log.info("Can Split: "+str(can_spilt))
    if 'SHARE' in os.environ:
        shared = os.getenv('SHARE').lower() in ['true']
        _multiprocess_shared_ = shared
        log.info("Shared: "+str(shared))

    @classmethod
    def setUpClass(cls):
        global i
        i += 1
        log.info("heavy operation is beginning... {0} {1}".format(i, cls.__name__))
        time.sleep(100 * quant)
        log.info("heavy operation has ended {0} {1}".format(i, cls.__name__))
        d("setUpClass {0} {1}".format(i, cls.__name__))

    @classmethod
    def tearDownClass(cls):
        d("tearDownClass " + cls.__name__)

    def test_one(self):
        time.sleep(3 * quant)
        d("1 " + self.__class__.__name__)

    def test_two(self):
        time.sleep(5 * quant)
        d("2 " + self.__class__.__name__)

    def test_three(self):
        time.sleep(8 * quant)
        d("3 " + self.__class__.__name__)

    def test_four(self):
        time.sleep(4 * quant)
        d("4 " + self.__class__.__name__)

    def test_five(self):
        time.sleep(6 * quant)
        d("5 " + self.__class__.__name__)


class SecondTest(FirstTest):
    def test_this(self):
        time.sleep(7 * quant)
        d("_2nd " + self.__class__.__name__)


class ThirdTest(FirstTest):
    def test_that(self):
        time.sleep(2 * quant)
        d("_3rd " + self.__class__.__name__)

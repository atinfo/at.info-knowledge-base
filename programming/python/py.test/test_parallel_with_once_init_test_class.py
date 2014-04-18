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
import unittest
import time
import logging
from threading import current_thread
from multiprocessing.process import current_process

def d(s):
    logging.getLogger().debug("{0} {1} {2} {3}".format(current_process().ident, current_thread().ident, time.time(), s))


i = 0  # per process counter
quant = 0.03  # increase if too fast

class TestFirst(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        global i
        i += 1
        d("heavy operation is beginning... {0} {1}".format(i, cls.__name__))
        time.sleep(100 * quant)
        d("heavy operation has ended {0} {1}".format(i, cls.__name__))
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


class TestSecond(TestFirst):
    def test_this(self):
        time.sleep(7 * quant)
        d("_2nd " + self.__class__.__name__)


class TestThird(TestFirst):
    def test_that(self):
        time.sleep(2 * quant)
        d("_3rd " + self.__class__.__name__)

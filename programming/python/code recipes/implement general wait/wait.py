from functools import wraps
import random
import time
import unittest
from contextlib import contextmanager


def wait(function, expected_condition=None, timeout=None, frequency=None):
    """simple implementation of wait mechanism"""
    if not timeout:
        timeout = 6

    if not frequency:
        frequency = 2

    if not expected_condition:
        def expected_condition(results):
            return results


    @wraps(function)
    def wrapper(*args, **kwargs):
        exception = None
        results = None
        for i in xrange(timeout / frequency):
            try:
                results = function(*args, **kwargs)
            except Exception, e:
                exception = e.message
            finally:
                if results:
                    if expected_condition:
                        if expected_condition(results):
                            break
                if timeout / frequency - i < 2:
                    break
                time.sleep(frequency)

        if exception:
            #todo: make your custom exception
            msg = "wrapped function exception {}".format(exception)
            raise Exception(msg)

        if not results:
            #todo: make your custom exception
            msg = "not retrieved results exception"
            raise Exception(msg)

        if results:
            if expected_condition:
                if not expected_condition(results):
                    #todo: make your custom exception
                    msg = "expected condition exception"
                    raise Exception(msg)
        return results

    return wrapper


@contextmanager
def assert_timeout_manager(expected_to_not_exceed_in_seconds=1):
    start = time.time()
    yield
    end = time.time()
    msg = "elapsed time is {}, but expected {}".format(end - start, expected_to_not_exceed_in_seconds)
    assert (end - start) <= expected_to_not_exceed_in_seconds, msg


def assert_timeout(expected_to_not_exceed_in_seconds=1):
    def method_decorator(func):
        @wraps(func)
        def wrapper(self, *argv, **kwargv):
            with assert_timeout_manager(expected_to_not_exceed_in_seconds):
                results = func(self, *argv, **kwargv)
            return results

        return wrapper

    return method_decorator


class TestWaiter(unittest.TestCase):
    default_timeout = 6
    default_frequency = 2

    @assert_timeout
    def test_wait_success(self):
        assert wait(lambda: True)()

    def method(self):
        time.sleep(1)
        return True

    def test_wait_success_call_method(self):
        class Some(object):
            def method(self):
                time.sleep(2)
                return True

        assert wait(self.method)()
        assert wait(Some().method)()

    @assert_timeout(default_timeout)
    def test_wait_with_delayed_success_result(self):
        def func():
            time.sleep(5)
            return True

        assert wait(func)()

    def test_wait_with_delayed_negative_result(self):
        def func():
            time.sleep(5)
            return False

        self.assertRaises(Exception, wait(func))

    def test_wait_with_success_result_exceed_timeout(self):
        def func():
            time.sleep(7)
            return True

        self.assertRaises(Exception, wait(func))

    def test_wait_with_exception_raised(self):
        def func():
            time.sleep(3)
            raise Exception("some internal exception")

        self.assertRaises(Exception, wait(func))

    def test_wait_set_timeout(self):
        timeout = 12

        def func():
            time.sleep(timeout - 2)
            return True

        start = time.time()
        wait(func, timeout=timeout)()
        end = time.time()
        assert timeout - 2 <= (end - start) <= timeout

    @assert_timeout(default_timeout)
    def test_wait_set_frequency(self):
        assert wait(lambda: True, frequency=1)()

    def test_wait_timeout_frequency(self):
        timeout = 12

        def func():
            time.sleep(timeout - 2)
            return True

        start = time.time()
        wait(func, timeout=timeout, frequency=4)()
        end = time.time()
        assert timeout - 2 <= (end - start) <= timeout

    def test_default_expected_conditions(self):
        with assert_timeout_manager():
            assert wait(lambda x: str(x))(123) == "123"
        with assert_timeout_manager(self.default_timeout):
            self.assertRaises(Exception, wait(lambda: []))
        with assert_timeout_manager(self.default_timeout):
            self.assertRaises(Exception, wait(lambda: 0))
        with assert_timeout_manager(self.default_timeout):
            self.assertRaises(Exception, wait(lambda: False))

    @assert_timeout(default_timeout)
    def test_wait_expected_condition_success(self):
        wait(lambda: [1, 2, 3], expected_condition=lambda x: 1 in x)

    def test_wait_expected_condition_fail(self):
        self.assertRaises(Exception, wait(lambda: [1, 2, 3], lambda x: 0 in x))

    def test_wait_with_all_arguments(self):
        timeout = 20

        def func():
            return [random.randint(0, 1) for _ in xrange(3)]

        def expected_condition(your_results):
            #some complex logic
            return your_results.count(1) == 3

        start = time.time()
        results = wait(func, expected_condition, timeout=timeout, frequency=2)()
        end = time.time()
        assert results == [1, 1, 1]
        assert (end - start) <= timeout, "elapsed time {}".format(end - start)


if __name__ == "__main__":
    unittest.main(verbosity=2)

package com.blogspot.notes.automation.qa.wrappers;

import com.blogspot.notes.automation.qa.interfaces.GenericPage;
import com.blogspot.notes.automation.qa.interfaces.SikuliDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import static java.lang.Thread.currentThread;
import static org.apache.commons.collections.MapUtils.isEmpty;
import static org.mockito.Mockito.*;

public abstract class BaseTest {

    private static final Logger LOGGER = Logger.getLogger(BaseTest.class.getName());

    private static final Collection<Thread> ALL_REMOTE_DRIVERS_THREADS = new ConcurrentLinkedQueue<>();
    private static final Map<Long, WebDriver> THREAD_REMOTE_DRIVER = new ConcurrentHashMap<>();

    private static final ThreadLocal<Map<GenericPage, BasePage>> PAGES =
            new ThreadLocal<Map<GenericPage, BasePage>>() {
                public Map<GenericPage, BasePage> initialValue() {
                    return new HashMap<>();
                }
            };

    private static final ThreadLocal<SikuliDriver> SIKULI_DRIVER = new ThreadLocal<SikuliDriver>() {
        public SikuliDriver initialValue() {
            return mock(SikuliDriver.class);
        }
    };

    private static AtomicInteger testsCounter = new AtomicInteger(0);
    private final AtomicBoolean cleanupThreadStarted = new AtomicBoolean(false);

    @BeforeMethod
    public void setUp(final Method method) {
        setDriver(new FirefoxDriver());
        logExecutionOrder(method);
    }

    @AfterMethod
    public void tearDown() {
        disposeDriver();
        cleanUpPages();
    }

    public static Map<GenericPage, BasePage> getPages() {
        return PAGES.get();
    }

    public static WebDriver getWebDriver() {
        return THREAD_REMOTE_DRIVER.get(currentThread().getId());
    }

    public static SikuliDriver getSikuliDriver() {
        return SIKULI_DRIVER.get();
    }

    private void cleanUpPages() {
        if (!isEmpty(getPages())) {
            PAGES.remove();
        }
    }

    private void logExecutionOrder(final Method method) {
        LOGGER.info("Executing test #" + testsCounter.incrementAndGet() + ": '" +
                method.getDeclaringClass().getSimpleName() + "." + method.getName());
    }

    private void closeUnusedDrivers() {
        ALL_REMOTE_DRIVERS_THREADS.stream().filter(thread -> !thread.isAlive()).forEach(this::disposeDriver);
    }

    private WebDriver setDriver(final WebDriver remoteDriver) {
        THREAD_REMOTE_DRIVER.put(currentThread().getId(), remoteDriver);
        return markForAutoClose(remoteDriver);
    }

    private void disposeDriver() {
        disposeDriver(currentThread());
    }

    private void disposeDriver(final Thread thread) {
        ALL_REMOTE_DRIVERS_THREADS.remove(thread);
        final WebDriver driver = THREAD_REMOTE_DRIVER.remove(thread.getId());
        if (driver != null) {
            driver.quit();
        }
    }

    private WebDriver markForAutoClose(final WebDriver webDriver) {
        ALL_REMOTE_DRIVERS_THREADS.add(currentThread());

        if (!cleanupThreadStarted.get()) {
            synchronized (cleanupThreadStarted) {
                if (!cleanupThreadStarted.get()) {
                    new UnusedRemoteDriversCleanupThread().start();
                    cleanupThreadStarted.set(true);
                }
            }
        }

        Runtime.getRuntime().addShutdownHook(new RemoteDriversFinalCleanupThread(currentThread()));

        return webDriver;
    }

    private class RemoteDriversFinalCleanupThread extends Thread {
        private final Thread thread;

        private RemoteDriversFinalCleanupThread(final Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            disposeDriver(thread);
        }
    }

    private class UnusedRemoteDriversCleanupThread extends Thread {
        private UnusedRemoteDriversCleanupThread() {
            setDaemon(true);
            setName("Web drivers' killer thread");
        }

        @Override
        public void run() {
            while (true) {
                closeUnusedDrivers();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
}

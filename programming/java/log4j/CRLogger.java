import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.*;
import org.testng.Reporter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;

/**
 * Logger for framework based on log4j with Tabs level
 */
public class CRLogger {
    static Logger LOG = Logger.getLogger("CRLogger");
    protected final static String BASIC_LOG_FILE = "log.log";
    private final static int TABS_LEVEL_0 = 0;
    private final static int TABS_LEVEL_1 = 1;
    private final static int TABS_LEVEL_2 = 2;
    private final static int TABS_LEVEL_3 = 3;
    protected static int currentTabsLevel;

    public static int Levels = -1;


    /**
     * Sets log level to level parsing by specified string. If level couldn't be found then DEBUG level will be set.
     *
     * @param level - log level
     */
    public static void setLogLevel(String level) {
        Level parseLevel = Level.toLevel(level);

        if (!parseLevel.equals(LOG.getLevel())) {
            LOG.setLevel(Level.INFO); // output the message
            LOG.info(String.format("Set LOG Level to: '%s'", parseLevel.toString()));
            LOG.setLevel(parseLevel);
        }
    }


    /**
     * Sets log level to DEBUG.
     */
    public static void setDebugOn() {
        if (!LOG.isDebugEnabled()) {
            setLogLevel("DEBUG");
        }
    }


    /**
     * Gets space indent by specified level (between 1 and 3). If unsupported value specified then empty string will
     * return.
     *
     * @param level - level of the indent.
     * @return string indent (spaces) according passed level.
     */
    private static String getLogIndent(int level) {
        return StringUtils.repeat("\t", level);
    }


    /**
     * Warning
     *
     * @param text - warning message
     */
    static protected void warningCore(String text) {
        LOG.warn(text);
    }


    /**
     * Info
     *
     * @param text - text for info
     */
    static public void info(String text) {
        String tabs = getLogIndent(currentTabsLevel);
        String mess = tabs + "INFO: " + text;
        infoCore(mess);
    }


    /**
     * InfoCore
     *
     * @param text - text for info
     */
    protected static void infoCore(String text) {
        LOG.info(text + "\n");
        Reporter.log(text + "\n <br />");
    }


    /**
     * Error
     *
     * @param text - text for error
     */
    static private void error(String text) {
        errorCore(text, null);
    }


    /**
     * Error
     *
     * @param text - error text
     * @param e    - throwable object (exception, error, cause)
     */
    protected static void errorCore(String text, Throwable e) {
        try {
            LOG.error(text + ((e == null) ? "" : e.getMessage()));

            // TODO add makeScreenshot functionality
            // Screenshot.makeScreenshot();
            if (e != null) {
                LOG.error(getStackTrace(e));
            }
        } catch (Exception ex) {
            LOG.error("ERROR DMLogger.error(): " + ex + "; error was : " + text);
            LOG.error(getStackTrace(ex));
            if (e != null) {
                LOG.error("ERROR exception: " + e.getMessage());
                LOG.error("ERROR stack trace : " + getStackTrace(e));
            }
            ex.printStackTrace();
        }
    }


    /**
     * Calls debug method from Log4j class.
     *
     * @param text - text will be wrote to the output.
     */
    protected static void debugCore(String text) {
        LOG.debug(text);
    }


    /**
     * Puts debug message to the console output.
     *
     * @param b - boolean value.
     */
    public static void debug(boolean b) {
        debug(String.valueOf(b));
    }


    /**
     * Puts debug message to the console output.
     *
     * @param i - integer value.
     */
    public static void debug(int i) {
        debug(String.valueOf(i));
    }


    /**
     * Puts debug message to the console output.
     *
     * @param text - debug message.
     */
    public static void debug(String text) {
        String tabs = getLogIndent(currentTabsLevel);
        String mess = tabs + "DEBUG: " + text;
        debugCore(mess);
    }


    /**
     * Puts debug message to the console output. Also will put stack trace of throwable if it not null.
     *
     * @param text - debug message.
     * @param e    - throwable object (exception, error, cause).
     */
    public static void debug(String text, Throwable e) {
        try {
            // if anybody pass message of exception as 'text'
            // it will prevent duplication of messages
            debug(text + ((e != null && !text.equals(e.getMessage())) ? e.getMessage() : ""));
            if (e != null) {
                debug(getStackTrace(e));
            }
        } catch (Exception ex) {
            LOG.error("ERROR DMLogger.error(): " + ex + "; error was : " + text);
            LOG.error(getStackTrace(ex));
            if (e != null) {
                LOG.error("ERROR exception: " + e.getMessage());
                LOG.error("ERROR stack trace : " + getStackTrace(e));
            }
            ex.printStackTrace();
        }
    }


    /**
     * trace
     *
     * @param text - text for trace
     */
    static public void trace(String text) {
        info(text);
    }


    /**
     * Get stack trace from throwable object
     *
     * @param e - throwable object (exception, error, cause).
     * @return String
     */
    static private String getStackTrace(Throwable e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        return stringWriter.toString();
    }


    /**
     * Add new file for logs (new log4j file appender)
     *
     * @param filename - new file name for logs.
     * @throws java.io.IOException
     */
    static public void addFileAppender(String filename) throws IOException {
        if (!BASIC_LOG_FILE.equals(filename)) {
            Enumeration<?> enumApp = LOG.getAllAppenders();

            boolean found = false;
            while (enumApp.hasMoreElements()) {
                Appender app = (Appender) enumApp.nextElement();
                if (app.getName() != null && filename.compareToIgnoreCase(app.getName()) == 0) {
                    found = true;
                    break;
                }
                if (app instanceof FileAppender && filename.compareToIgnoreCase(((FileAppender) app).getFile()) == 0) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                FileAppender app = new FileAppender(new PatternLayout("%d [%-5p] - %m%n"), filename);
                app.setName(filename);
                LOG.addAppender(new FileAppender(new PatternLayout("%d [%-5p] - %m%n"), filename));
            }
        }
    }


    /**
     * Remove file for logs (remove log4j file appender)
     *
     * @param filename - file name for logs
     * @throws IOException
     */
    static public void removeFileAppender(String filename) throws IOException {
        if (!BASIC_LOG_FILE.equals(filename)) {
            Enumeration<?> enumApp = LOG.getAllAppenders();

            while (enumApp.hasMoreElements()) {
                Appender app = (Appender) enumApp.nextElement();
                if (app.getName() != null && filename.compareToIgnoreCase(app.getName()) == 0) {
                    LOG.removeAppender(app);
                    break;
                }
                if (app instanceof FileAppender && filename.compareToIgnoreCase(((FileAppender) app).getFile()) == 0) {
                    LOG.removeAppender(app);
                    break;
                }
            }
        }
    }


    /**
     * Page classes logs.
     *
     * @param args - methods arguments
     */
    public static void methodStarted(Object... args) {
        methodStarted(3, TABS_LEVEL_2, args);
    }


    /**
     * Page classes logs.
     *
     * @param int      - executing method
     * @param tablevel - tab indent
     * @param args     - methods args
     */
    protected static void methodStarted(int j, int tabLevel, Object... args) {

        StringBuilder params = new StringBuilder();
        currentTabsLevel = tabLevel;
        for (int i = 0; i < args.length; i++) {

            params.append(args[i]);
            if (args.length > 1 && i < (args.length - 1)) {
                params.append(", ");
            }
        }
        String tabs = getLogIndent(tabLevel);
        String mess = tabs + "STARTED  " + getExecutingMethod(j) + "()";

        if (args.length > 0) {
            mess += " with params: " + params.toString();
        }

        infoCore(mess);
    }


    /**
     * Page classes logs.
     *
     * @param res - return value of method
     */
    public static void methodFinished(Object... res) {
        methodFinished(3, TABS_LEVEL_2, res);
    }


    /**
     * Page classes logs.
     *
     * @param int      - executing method
     * @param tablevel - tab indent
     * @param args     - methods args
     */
    protected static void methodFinished(int j, int tabLevel, Object... res) {

        StringBuilder params = new StringBuilder();

        for (int i = 0; i < res.length; i++) {
            params.append(res[i]);

            if (res.length > 1 && i < (res.length - 1)) {
                params.append(", ");
            }
        }
        String tabs = getLogIndent(tabLevel);
        String mess = tabs + "FINISHED " + getExecutingMethod(j) + "()";
        if (res.length > 0) {
            mess += " with results: " + params.toString();
        }
        infoCore(mess);
    }


    /**
     * Error in the method log
     *
     * @param e - throwable object (exception, error, cause).
     */
    public static void errorInMethod(Throwable e) {
        String mess = "!!!  Error in " + getExecutingMethod(2) + "(): ";
        errorCore(mess, e);
    }


    /**
     * Error in the method log
     *
     * @param error - error text
     * @param e     - throwable object (exception, error, cause).
     */
    public static void errorInMethod(String error, Throwable e) {
        String mess = "!!!  Error in " + getExecutingMethod(2) + "(): " + error;
        errorCore(mess, e);
    }


    /**
     * Error in the method log
     *
     * @param errorMeth - error method name
     */
    public static void errorInMethod(String errorMeth) {
        String tabs = getLogIndent(currentTabsLevel);

        String mess = "!!!  Error in " + getExecutingMethod(2) + "(): " + errorMeth;
        error(tabs + mess);
    }


    /**
     * Print additional information
     *
     * @param info - info text
     */
    public static void additionalInfo(String info) {
        infoCore("====== " + info);
    }


    /**
     * Get executing method name.
     *
     * @return String - execution methods name
     */
    public static String getExecutingMethod(int j) {
        Throwable t = new Throwable();
        StackTraceElement[] ste = t.getStackTrace();

        String fullClassPath = ste[j].getClassName();
        String[] path = fullClassPath.split("\\.");
        String className = path[(path.length - 1)];

        return className + "." + ste[j].getMethodName();
    }


    /**
     * Test case classes log
     *
     * @param args - method args
     */
    public static void methodStartedTest(Object... args) {
        methodStarted(3, TABS_LEVEL_0, args);
    }


    /**
     * Test case classes log
     *
     * @param args - return value
     */
    public static void methodFinishedTest(Object... args) {
        methodFinished(3, TABS_LEVEL_0, args);
    }


    /**
     * Flow classes log
     *
     * @param args - method args
     */
    public static void methodStartedFlow(Object... args) {
        methodStarted(3, TABS_LEVEL_1, args);
    }


    /**
     * Flow classes log
     *
     * @param args - return values
     */
    public static void methodFinishedFlow(Object... args) {
        methodFinished(3, TABS_LEVEL_1, args);
    }


    /**
     * Core methods log
     *
     * @param args - method args
     */
    public static void methodStartedCore(Object... args) {
        methodStarted(3, TABS_LEVEL_3, args);
    }


    /**
     * Core methods log
     *
     * @param args - return value
     */
    public static void methodFinishedCore(Object... args) {
        methodFinished(3, TABS_LEVEL_3, args);
    }
}

package info.testing.automated.core;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.functions.BundleFunctions;
import info.testing.automated.entities.Suites;
import info.testing.automated.entities.TestResults;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * Author: Serhii Kuts
 */
public class BaseHTMLReporter implements IReporter {

    private static final Logger LOGGER = Logger.getLogger(BaseHTMLReporter.class.getName());

    private static final String LOCALE_ARG = "locale";
    private static final String REPORT_TITLE_ARG = "report.title";
    private static final String DEFAULT_REPORT_TITLE = "Test results";

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("EEEE dd MMMM yyyy", Locale.ENGLISH);
    private static final DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm z");

    private static final String IGNORE_SUITE = "Base suite";
    private static final String SUITES_TEMPLATE_VAR = "suites";
    private static final String REPORT_TITLE_TEMPLATE_VAR = "reportTitle";
    private static final String REPORT_DATE_TEMPLATE_VAR = "reportDate";
    private static final String TRANSLATE_VAR = "translate";

    private static final String REPORT_TEMPLATE = "templates/report.mustache";
    private static final String REPORT_OUTPUT = "html/report.html";

    private static final String RESOURCE_BUNDLE_PATH = "localization.report";

    @Override
    public void generateReport(final List<XmlSuite> xmlSuites, final List<ISuite> suites, final String outputDirectory) {
        try {
            final Mustache mustache = new DefaultMustacheFactory().compile(REPORT_TEMPLATE);
            final File reportsFolder = new File(outputDirectory + "/html");

            reportsFolder.mkdir();

            if (reportsFolder.exists()) {
                mustache.execute(new FileWriter(outputDirectory + "/" + REPORT_OUTPUT), getScope(suites)).flush();
            }
        } catch (Exception e) {
            LOGGER.severe("Can't create template: " + e.getMessage());
        }
    }

    private Map<String, Object> getScope(final List<ISuite> suites) {
        final Date currentDate = new Date();
        final String formattedDate = DATE_FORMAT.format(currentDate) + ", " + TIME_FORMAT.format(currentDate);

        final List<Suites> suitesList = new ArrayList<>();
        final List<TestResults> suiteResults = new ArrayList<>();
        final Map<String, Object> scope = new HashMap<>();

        for (ISuite suite : suites) {
            if (suite.getName().equals(IGNORE_SUITE)) {
                continue;
            }

            final List<ISuiteResult> results = new ArrayList<>(suite.getResults().values());

            for (ISuiteResult result : results) {
                suiteResults.add(new TestResults(result.getTestContext()));
            }

            suitesList.add(new Suites(suite.getName(), suiteResults));
        }

        Collections.sort(suitesList, Suites.suitesComparator());

        scope.put(SUITES_TEMPLATE_VAR, suitesList);
        scope.put(REPORT_TITLE_TEMPLATE_VAR, System.getProperty(REPORT_TITLE_ARG, DEFAULT_REPORT_TITLE));
        scope.put(REPORT_DATE_TEMPLATE_VAR, formattedDate);
        scope.put(TRANSLATE_VAR, BundleFunctions.newPreTranslate(RESOURCE_BUNDLE_PATH, getLocale()));

        return scope;
    }

    private Locale getLocale() {
        if (System.getProperties().containsKey(LOCALE_ARG)) {
            final String locale = System.getProperty(LOCALE_ARG);
            final String[] components = locale.split("_", 3);

            switch (components.length) {
                case 1:
                    return new Locale(locale);
                case 2:
                    return new Locale(components[0], components[1]);
                case 3:
                    return new Locale(components[0], components[1], components[2]);
                default:
                    LOGGER.severe("Invalid locale specified: " + locale);
            }
        }

        return Locale.getDefault();
    }
}

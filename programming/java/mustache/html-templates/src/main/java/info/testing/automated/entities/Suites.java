package info.testing.automated.entities;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Author: Serhii Kuts
 */
public class Suites {

    private String name;
    private List<TestResults> testResults;

    public Suites(final String name, final List<TestResults> testResults) {
        this.name = name;
        this.testResults = testResults;
        Collections.sort(this.testResults, TestResults.testResultsComparator());
    }

    public List<TestResults> getTestResults() {
        return testResults;
    }

    public String getName() {
        return name;
    }

    public static Comparator<Suites> suitesComparator() {
        return new Comparator<Suites>() {
            @Override
            public int compare(final Suites object1, final Suites object2) {
                return object1.getName().compareTo(object2.getName());
            }
        };
    }
}

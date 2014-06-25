package info.at;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Evgeniya
 * Date: 3/5/14
 * Time: 12:32 PM
 */
@SuppressWarnings("unused")
public class Result {
    private Map<String, String> results;

    public void addResult(String methodName, String state) {
        if (results == null) {
            results = new HashMap<String, String>();
        }
        results.put(methodName, state);
    }

    public Set<Class> getFailedTestClasses() {
        Set<Class> failedTests = new HashSet<Class>();
        List<String> className = new ArrayList<String>();
        for (Map.Entry<String, String> result : results.entrySet()) {
            if (result.getValue().toLowerCase().equals("failure")) {
                className.add(result.getKey());
            }
        }
        Collections.sort(className);
        String ts = "junit.framework.TestSuite: junit.framework.TestSuite.";
        for (String name : className) {
            if (name.startsWith(ts)) {
                name = name.replace(ts, "");
            }
            failedTests.add(getClassForFailedTest(name));
        }
        return failedTests;
    }

    private Class getClassForFailedTest(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            String[] paths = name.split("\\.");
            String tmp = "";
            for (String path : paths) {
                tmp += path;
                try {
                    return Class.forName(tmp);
                } catch (ClassNotFoundException ef) {
                    tmp += ".";
                }
            }
            return null;
        }
    }

    public Set<Class> getClasses() {
        Set<Class> classes = new HashSet<Class>();
        List<String> className = new ArrayList<String>();
        for (Map.Entry<String, String> result : results.entrySet()) {
            className.add(result.getKey());
        }
        Collections.sort(className);
        String ts = "junit.framework.TestSuite: junit.framework.TestSuite.";
        for (String name : className) {
            if (name.startsWith(ts)) {
                name = name.replace(ts, "");
            }
            classes.add(getClassForFailedTest(name));
        }
        return classes;

    }
}

package info.at.suites;

import info.at.TeamCity;
import junit.framework.JUnit4TestAdapter;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.AllTests;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Evgeniya
 * Date: 5/8/14
 * Time: 9:28 AM
 */
@RunWith(AllTests.class)
public final class RunFailedTestsByBuildID {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        Set<Class> classes = null;
        try {
            classes = new TeamCity().getBuildResults(740).getFailedTestClasses();
        } catch (Exception e) {/**/}
        if (classes != null)
            for (Class failedTestClass : classes) {
                if (failedTestClass != null) {
                    suite.addTest(new JUnit4TestAdapter(failedTestClass));
                }

            }
        return suite;
    }
}

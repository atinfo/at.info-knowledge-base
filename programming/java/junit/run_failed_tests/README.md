# Java example: how to rerun the only failed tests from TeamCity build

Implemented via maven, junit.

**Main usage**: collecting failed tests during tests execution into junit test suite so then you can rerun only failed tests up to your needs

Related discussion of described topic http://automated-testing.info/t/avtomaticheskij-perezapusk-upavshih-testov-s-pomoshhyu-java-maven-teamcity-junit/4712

Inside of project you will see implementation for getting test results from:
 * TeamCity build by build id
 * Latest TeamCity build
 * TeamCity tests csv file
 * Filtering tool for getting only failed tests
 * JUnit test suites for all that cases

If you want to run tests from defined build, you should set maven parameter -Dbuild.type as descriptor for build you need

```java
@RunWith(AllTests.class)
public final class RunLatestBuildFailedTests {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        Set<Class> classes = null;
        try {
            classes = new TeamCity().getLatestBuildResults().getFailedTestClasses();
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
```
If you CI server is available via ssh only, set up ssh connection properties in the  ssh.tunnel.properties file and use

```java
new TeamCity().openSSHTunnel().getLatestBuildResults()
```

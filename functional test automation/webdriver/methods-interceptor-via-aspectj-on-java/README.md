AspectJ example: how to intercept methods calls for collecting test steps via AOP
======

Created as analogue for the following recipe: https://github.com/atinfo/at.info-knowledge-base/tree/master/functional%20test%20automation/webdriver/methods-interceptor-on-java.
But in comparison to java reflection, this particular code uses AOP / aspects for resolving the same task. 

Implemented via maven, testng, aspectj.

Alos please see related developers discussions on http://automated-testing.info/t/code-recipe-ispolzovanie-java-reflection-dlya-monitoringa-vyzovov-metodov-testovogo-klassa-s-maven-testng-i-javassist/4592

Main usage: collecting steps that were intercepted during tests' execution. This data could be used in
test results report for increasing understanding of what was tested.

Inside of project you will see: 
 
 - StepsLogger aspect for intercepting methods calls
 - Custom annotation for tracking what methods need to be intercepted
 - Custom page factory implementation for creating page objects only when they needed
 - Simple page object and base test for test background preparation
 - Simple test that prints used annotated methods' names


Test looks like the following:
```java
package info.testing.automated.tests;

import info.testing.automated.core.BaseTest;
import org.testng.annotations.Test;

import static info.testing.automated.core.StepsLogger.getMethodsCallsList;

/**
 * Author: Serhii Kuts
 */
public class MethodsInterceptorTests extends BaseTest {

    @Test
    public void interceptAndPublish() throws InterruptedException {
        samplePage()
                .firstAction("firstArg")
                .secondAction()
                .thirdAction("thirdArg")
                .fourthAction();

        for (String method : getMethodsCallsList()) {
            System.out.println(method);
        }

        Thread.sleep(3000);
    }
}
```
And implementetion of aspect looks like this:

```java
@Aspect
public class StepsLogger {

    private static final ThreadLocal<List<String>> METHODS_CALLS = new ThreadLocal<>();

    @Before("execution(@info.testing.automated.annotations.Publish * info.testing.automated.pages..*.*(..))")
    public void beforeInvocation(final JoinPoint joinPoint) throws Throwable {
        getMethodsCallsList().add(joinPoint.getSignature().getName() +
                "(" + Arrays.deepToString(joinPoint.getArgs()).replaceAll("\\[|\\]", "") + ")");
    }

    public static List<String> getMethodsCallsList() {
        if (METHODS_CALLS.get() == null) {
            METHODS_CALLS.set(new ArrayList<String>());
        }

        return METHODS_CALLS.get();
    }

    public static void clearMethodsCallsList() {
        if (METHODS_CALLS.get() != null) {
            METHODS_CALLS.remove();
        }
    }
}
```

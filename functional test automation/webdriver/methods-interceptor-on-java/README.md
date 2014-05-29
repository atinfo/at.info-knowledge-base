Java example: how to intercept methods calls for collecting test steps
======

Implemented via maven, testng.

Main usage: collecting steps that were intercepted during tests' execution. This data could be used in
test results report for increasing understanding of what was tested.

Example description and discussion on http://automated-testing.info/t/code-recipe-ispolzovanie-java-reflection-dlya-trekinga-vyzovov-metodov-testa-s-maven-testng-i-javassist/4592

Inside of project you will see: 
 
 - Reflection utils implementation for intercepting methods calls
 - Custom annotations for tracking what methods need to be intercepted
 - Custom page factory implementation for creating page objects via reflection with methods interceptor
 - Simple page object and base test for test background preparation
 - Simple test that prints used annotated methods' names 


Test looks like the following:
```java
package info.testing.automated.tests;

import info.testing.automated.core.BaseTest;
import info.testing.automated.utils.ReflectionUtils;
import org.testng.annotations.Test;

import static info.testing.automated.utils.ReflectionUtils.getMethodsCallsList;

/**
 * Author: Serhii Kuts
 */
public class MethodsInterceptorTests extends BaseTest {

    @Test
    public void interceptAndPublish() throws Exception {
        samplePage()
                .firstAction()
                .secondAction()
                .thirdAction()
                .fourthAction();

        for (String method : getMethodsCallsList()) {
            System.out.println(method);
        }

        Thread.sleep(3000);
    }
}
```

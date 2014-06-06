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

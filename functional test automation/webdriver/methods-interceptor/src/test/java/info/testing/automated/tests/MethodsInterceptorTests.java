package info.testing.automated.tests;

import info.testing.automated.core.BaseTest;
import info.testing.automated.utils.ReflectionUtils;
import org.testng.annotations.Test;

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

        for (String method : ReflectionUtils.getMethodsCallsList()) {
            System.out.println(method);
        }

        Thread.sleep(3000);
    }
}

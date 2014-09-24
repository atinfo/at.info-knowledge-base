package info.at.tagging;

import info.at.runners.concurrent_parametrized.ConcurrentParameterized;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.AssumptionViolatedException;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: TJ
 * Date: 24.09.14
 * Time: 21:17
 */
@RunWith(ConcurrentParameterized.class)
public class TestRunner {
    @Parameterized.Parameter
    public Method testMethod;

    @Parameterized.Parameters(name = "{0}")
    public static Collection data() throws ClassNotFoundException {
        String tagsParam = System.getProperty("tags");
        List<Object[]> data = new ArrayList<>();
        Set<Method> methods = new HashSet<>();
        if (tagsParam == null) return data;
        for (String tag : tagsParam.split(",\\s")) {
            methods.addAll(getMethodsForTag(tag));
        }
        for (Method method : methods) {
            data.add(new Object[]{method});
        }
        return data;
    }

    @Test
    public void test() {
        Request request = Request.method(testMethod.getDeclaringClass(), testMethod.getName());
        Result result = new JUnitCore().run(request);
        if (result.getIgnoreCount() > 0)
            throw new AssumptionViolatedException("Test " + testMethod.getDeclaringClass()
                    + "." + testMethod.getName() + " were ignored");
        if (result.getFailureCount() > 0) {
            Assert.fail(result.getFailures().toString());
        }
    }

    private static Set<Method> getMethodsForTag(String tag) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("info.at.tagging"))
                .setScanners(new MethodAnnotationsScanner()));
        Set<Method> testMethods = new HashSet<>();
        Set<Method> allMethods = reflections.getMethodsAnnotatedWith(Tag.class);
        for (Method klass : allMethods) {
            if (Arrays.asList(klass.getAnnotation(Tag.class).value()).contains(tag)) {
                testMethods.add(klass);
            }
        }
        return testMethods;
    }
}

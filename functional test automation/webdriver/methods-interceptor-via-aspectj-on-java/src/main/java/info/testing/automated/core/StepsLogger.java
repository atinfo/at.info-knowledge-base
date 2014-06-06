package info.testing.automated.core;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Serhii Kuts
 */
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

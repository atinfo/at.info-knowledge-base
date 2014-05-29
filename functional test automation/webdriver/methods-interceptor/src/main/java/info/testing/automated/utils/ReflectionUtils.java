package info.testing.automated.utils;

import info.testing.automated.annotations.Publish;
import info.testing.automated.annotations.ThrowsException;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Serhii Kuts
 */
public final class ReflectionUtils {

    private static final ThreadLocal<List<String>> METHODS_CALLS = new ThreadLocal<>();

    private ReflectionUtils() {
    }

    @SuppressWarnings(value = "unchecked")
    public static Object getInstance(final Class clazz, final Object... args) {
        Object instance;

        try {
            if (args != null && args.length > 0) {
                final Class[] classes = new Class[args.length];
                int counter = 0;

                for (Object arg : args) {
                    classes[counter++] = arg.getClass();
                }

                instance = clazz.getConstructor(classes).newInstance(args);
            } else {
                instance = clazz.getConstructor().newInstance();
            }
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            // log error here
            instance = null;
        }

        return instance;
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> T create(final Class<T> clazz, final Object... args) {
        final ProxyFactory factory = new ProxyFactory();
        factory.setSuperclass(clazz);
        factory.setFilter(getPackageFilter(clazz.getPackage().getName()));

        final MethodHandler handler = new MethodHandler() {
            @Override
            public Object invoke(final Object self, final Method overridden, final Method forwarder,
                                 final Object[] args) throws Exception {
                getMethodsCallsList().add(overridden.getName() +
                        "(" + Arrays.deepToString(args).replaceAll("\\[|\\]", "") + ")");

                try {
                    return forwarder.invoke(self, args);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    // throw your custom exception for annotated methods, otherwise - skip
                    if (overridden.getAnnotation(ThrowsException.class) != null) {
                        throw new Exception();
                    }
                    return null;
                }
            }
        };

        final Object instance = getInstance(factory.createClass(), args);

        if (instance != null) {
            ((ProxyObject) instance).setHandler(handler);
        }

        return (T) instance;
    }

    public static MethodFilter getPackageFilter(final String packageName) {
        return new MethodFilter() {
            public boolean isHandled(final Method method) {
                return method.getDeclaringClass().getName().contains(packageName) &&
                        method.getAnnotation(Publish.class) != null;
            }
        };
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

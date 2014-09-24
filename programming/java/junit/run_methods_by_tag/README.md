# Java example: how to run the only test methods, which has a special tag

Implemented via maven, junit.

**Main usage**: collecting test methods with a custom tag and running them in a multithread mode

Related discussion of described topic http://automated-testing.info/t/grupirovka-i-zapusk-testov-po-tegam/5092

Inside of project you will see implementation for:
 * Custom concurrent parametrized runner
 * Tag annotation, which implements String usage, but can be replaced with a Class easily
 * Set of JUnit tests, which allow you to test feature

If you want to run tests with a tag "tag1", you should set maven running parameters to:
maven goal: test
additional maven parameters: -Dtest=TestRunner -Dtags=tag1

```java
@RunWith(ConcurrentParameterized.class)
public class TestRunner {
    @Parameterized.Parameter
    public Method testMethod;

    @Parameterized.Parameters(name = "{0}")
    public static Collection data() throws ClassNotFoundException {
        System.setProperty("tags", "tag1");
        String tagsParam = System.getProperty("tags");
        List<Object[]> data = new ArrayList<>();
        List<Method> methods = new ArrayList<>();
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
```
If you have a set of tests like this one

```java
public class TestsClass {
    @Tag({"tag1", "tag3"})
    @Test
    public void test1() {
        System.out.println("TestsClass Test 1 running");
    }

    @Tag("tag1")
    @Test
    public void test3() {
        System.out.println("TestsClass Test 3 running");
    }


    @Tag({"tag2", "tag3"})
    @Test
    public void test2() {
        System.out.println("TestsClass Test 2 running");
    }
}
```
you will see test1 and test3 running

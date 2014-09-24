package info.at.tests;


import info.at.tagging.Tag;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: TJ
 * Date: 24.09.14
 * Time: 21:15
 */
public class TestsClass2 {
    @Tag({"tag1", "tag3"})
    @Test
    public void test1() {
        System.out.println("TestsClass2 Test 1 running");
    }

    @Tag("tag1")
    @Test
    public void test3() {
        System.out.println("TestsClass2 Test 3 running");
    }


    @Tag({"tag2", "tag3"})
    @Test
    public void test2() {
        System.out.println("TestsClass2 Test 2 running");
    }
}

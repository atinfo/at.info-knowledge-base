package info.testing.automated.tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

/**
 * Author: Serhii Kuts
 */
public class ReportTests {

    @Test
    public void fillInReport1() {
    }

    @Test
    public void fillInReport2() {
        Assert.fail();
    }

    @Test
    public void fillInReport3() {
        throw new SkipException("Skipping test");
    }
}

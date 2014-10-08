package info.testing.automated.entities;

import org.testng.ITestContext;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Author: Serhii Kuts
 */
public class TestResults {

    private static final NumberFormat DURATION_FORMAT = new DecimalFormat("#0.000");
    private static final NumberFormat PASS_RATE_FORMAT = new DecimalFormat("#0.00");

    private String name;

    private int passed;
    private int skipped;
    private int failed;

    private Date startDate;
    private Date endDate;

    private String passRate;
    private String duration;

    private String highlightPassed;
    private String highlightFailed;
    private String highlightSkipped;

    private enum Highlight {
        PASSED,
        SKIPPED,
        FAILED,
        ZERO
    }

    public TestResults(final ITestContext context) {
        this.name = context.getName();
        this.passed = context.getPassedTests().size();
        this.failed = context.getFailedTests().size();
        this.skipped = context.getSkippedTests().size();
        this.startDate = context.getStartDate();
        this.endDate = context.getEndDate();

        final int totalTests = passed + failed + skipped;
        final int incompleteTests = failed + skipped;

        this.passRate = PASS_RATE_FORMAT.format((double) (totalTests - incompleteTests) * 100 / totalTests);
        this.duration = DURATION_FORMAT.format((double) (endDate.getTime() - startDate.getTime()) / 1000);

        this.highlightPassed = passed > 0 ? Highlight.PASSED.name().toLowerCase() : Highlight.ZERO.name().toLowerCase();
        this.highlightFailed = failed > 0 ? Highlight.FAILED.name().toLowerCase() : Highlight.ZERO.name().toLowerCase();
        this.highlightSkipped = skipped > 0 ? Highlight.SKIPPED.name().toLowerCase() : Highlight.ZERO.name().toLowerCase();
    }

    public String getName() {
        return name;
    }

    public int getPassed() {
        return passed;
    }

    public int getSkipped() {
        return skipped;
    }

    public int getFailed() {
        return failed;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getPassRate() {
        return passRate;
    }

    public String getDuration() {
        return duration;
    }

    public String getHighlightPassed() {
        return highlightPassed;
    }

    public String getHighlightFailed() {
        return highlightFailed;
    }

    public String getHighlightSkipped() {
        return highlightSkipped;
    }

    public static Comparator<TestResults> testResultsComparator() {
        return new Comparator<TestResults>() {
            @Override
            public int compare(final TestResults object1, final TestResults object2) {
                return object1.getName().compareTo(object2.getName());
            }
        };
    }
}

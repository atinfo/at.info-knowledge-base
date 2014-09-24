package info.at.runners.concurrent_parametrized;

import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.Parameterized;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Evgeniya
 * Date: 6/23/14
 * Time: 12:31 PM
 */
public class ConcurrentParameterized extends Parameterized {

    private ConcurrentRunnerScheduler scheduler;

    public ConcurrentParameterized(Class<?> klass) throws Throwable {
        super(klass);
        scheduler = new ConcurrentRunnerScheduler(klass);
        setScheduler(scheduler);
    }

    @Override
    public void run(RunNotifier notifier) {
        super.run(notifier);
        scheduler.suiteFinished();
    }

    @Override
    protected List<Runner> getChildren() {
        for (Runner runner : super.getChildren()) {
            BlockJUnit4ClassRunner classRunner = (BlockJUnit4ClassRunner) runner;
            classRunner.setScheduler(scheduler);
        }
        return super.getChildren();
    }
}
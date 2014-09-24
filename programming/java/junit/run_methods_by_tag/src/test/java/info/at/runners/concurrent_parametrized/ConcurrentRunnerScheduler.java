package info.at.runners.concurrent_parametrized;

import com.googlecode.jeeunit.concurrent.Concurrent;
import com.googlecode.jeeunit.concurrent.impl.NamedThreadFactory;
import org.junit.runners.model.RunnerScheduler;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: Evgeniya
 * Date: 6/23/14
 * Time: 12:31 PM
 */
public class ConcurrentRunnerScheduler implements RunnerScheduler {

    private ExecutorService executorService;
    private CompletionService<Void> completionService;
    private Queue<Future<Void>> tasks = new LinkedList<>();

    public ConcurrentRunnerScheduler(Class<?> klass) {
        String param = System.getProperty("threads");
        int numThreads = param == null ? (klass.isAnnotationPresent(Concurrent.class) ? klass
                .getAnnotation(Concurrent.class).threads() : 1) : Integer.parseInt(param);
        executorService = Executors.newFixedThreadPool(numThreads,
                new NamedThreadFactory(klass.getSimpleName()));
        completionService = new ExecutorCompletionService<>(executorService);
    }

    @Override
    public void schedule(Runnable childStatement) {
        tasks.offer(completionService.submit(childStatement, null));
    }

    @Override
    public void finished() {
    }

    public void suiteFinished() {
        try {
            while (!tasks.isEmpty())
                tasks.remove(completionService.take());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            while (!tasks.isEmpty())
                tasks.poll().cancel(true);
            executorService.shutdownNow();
        }
    }
}

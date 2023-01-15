package mc.obliviate.arenacore.match.task;

import java.time.Duration;

public interface ITaskScheduler {

    void sync(Runnable runnable);

    void async(Runnable runnable);

    default void delaySync(Runnable runnable, Duration delay) {
        delayAsync(runnable, delay.toMillis() / 50);
    }

    void delaySync(Runnable runnable, long delay);

    default void delayAsync(Runnable runnable, Duration delay) {
        delayAsync(runnable, delay.toMillis() / 50);
    }

    void delayAsync(Runnable runnable, long delay);

    default void periodicSync(Runnable runnable, Duration delay, Duration period) {
        periodicSync(runnable, delay.toMillis() / 50, period.toMillis() / 50);
    }

    void periodicSync(Runnable runnable, long delay, long period);

    default void periodicAsync(Runnable runnable, Duration delay, Duration period) {
        periodicAsync(runnable, delay.toMillis() / 50, period.toMillis() / 50);
    }

    void periodicAsync(Runnable runnable, long delay, long period);

}

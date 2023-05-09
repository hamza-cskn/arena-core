package mc.obliviate.arenacore.task;

import mc.obliviate.arenacore.util.Preconditions;

import java.time.Duration;

public abstract class TaskScheduler {

    private static TaskScheduler instance;

    public static void setInstance(TaskScheduler instance) {
        TaskScheduler.instance = instance;
    }

    public static TaskScheduler getInstance() {
        Preconditions.checkNotNull(instance, "TaskScheduler instance is not initialized!");
        return instance;
    }

    public abstract void sync(Runnable runnable);

    public abstract void async(Runnable runnable);

    public void delaySync(Runnable runnable, Duration delay) {
        delayAsync(runnable, delay.toMillis() / 50);
    }

    public abstract void delaySync(Runnable runnable, long delay);

    public void delayAsync(Runnable runnable, Duration delay) {
        delayAsync(runnable, delay.toMillis() / 50);
    }

    public abstract void delayAsync(Runnable runnable, long delay);

    public void periodicSync(Runnable runnable, Duration delay, Duration period) {
        periodicSync(runnable, delay.toMillis() / 50, period.toMillis() / 50);
    }

    public abstract void periodicSync(Runnable runnable, long delay, long period);

    public void periodicAsync(Runnable runnable, Duration delay, Duration period) {
        periodicAsync(runnable, delay.toMillis() / 50, period.toMillis() / 50);
    }

    public abstract void periodicAsync(Runnable runnable, long delay, long period);

}

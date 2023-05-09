package mc.obliviate.arenacore.task;

import mc.obliviate.arenacore.match.task.MatchTask;

import java.time.Duration;
import java.util.List;
import java.util.function.BooleanSupplier;

public interface Task {

    Task after(long delay);

    Task after(Duration delay);

    Task async();

    Task sync();

    Task every(Duration period);

    Task every(long period);

    Task freezeIf(BooleanSupplier condition);

    Task termianteIf(BooleanSupplier condition);

    List<BooleanSupplier> getFreezeConditions();

    List<BooleanSupplier> getTerminateConditions();

    boolean checkConditions(List<BooleanSupplier> conditions);

    MatchTask whenFinished(Runnable endRunnable);

    MatchTask.State getCurrentState();

    long getDelay();

    long getPeriod();

    Runnable getEndRunnable();

    Runnable getMainRunnable();

    void run(Runnable runnable);

    void cancel();


}

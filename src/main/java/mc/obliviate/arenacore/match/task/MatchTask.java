package mc.obliviate.arenacore.match.task;

import mc.obliviate.arenacore.task.Task;
import mc.obliviate.arenacore.task.TaskScheduler;
import mc.obliviate.arenacore.util.Preconditions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class MatchTask implements Task {

    private State currentState = State.UNBORN;
    private long period = -1L;
    private long delay = -1L;
    private boolean async = false;

    private Runnable mainRunnable;
    private Runnable endRunnable;

    private final List<BooleanSupplier> freezeConditions = new ArrayList<>();
    private final List<BooleanSupplier> terminateConditions = new ArrayList<>();

    public MatchTask() {

    }

    @Override
    public MatchTask after(long delay) {
        this.delay = delay;
        return this;
    }

    @Override
    public MatchTask after(Duration delay) {
        this.delay = delay.toMillis() / 50L;
        return this;
    }

    @Override
    public MatchTask async() {
        this.async = true;
        return this;
    }

    @Override
    public MatchTask sync() {
        this.async = false;
        return this;
    }

    @Override
    public MatchTask every(Duration period) {
        this.period = period.toMillis() / 50L;
        return this;
    }

    @Override
    public MatchTask every(long period) {
        this.period = period;
        return this;
    }

    @Override
    public MatchTask freezeIf(BooleanSupplier condition) {
        this.freezeConditions.add(condition);
        return this;
    }

    @Override
    public MatchTask termianteIf(BooleanSupplier condition) {
        this.terminateConditions.add(condition);
        return this;
    }

    @Override
    public List<BooleanSupplier> getFreezeConditions() {
        return freezeConditions;
    }

    @Override
    public List<BooleanSupplier> getTerminateConditions() {
        return terminateConditions;
    }

    @Override
    public boolean checkConditions(List<BooleanSupplier> conditions) {
        return conditions.stream().allMatch(BooleanSupplier::getAsBoolean);
    }

    @Override
    public MatchTask whenFinished(Runnable endRunnable) {
        this.endRunnable = endRunnable;
        return this;
    }

    @Override
    public State getCurrentState() {
        return currentState;
    }

    @Override
    public long getDelay() {
        return delay;
    }

    @Override
    public long getPeriod() {
        return period;
    }

    @Override
    public Runnable getEndRunnable() {
        return endRunnable;
    }

    @Override
    public Runnable getMainRunnable() {
        return mainRunnable;
    }

    @Override
    public void run(Runnable runnable) {
        Preconditions.checkState(this.currentState == State.UNBORN, "This task has already been run before");
        Preconditions.checkNotNull(runnable, "Main Runnable cannot be null!");
        this.currentState = State.RUNNING;
        this.mainRunnable = runnable;
        if (endRunnable == null) endRunnable = () -> {
        };
        tick();
    }

    private synchronized void tick() {
        if (this.currentState == State.TERMINATED) return;
        if (this.checkConditions(this.terminateConditions)) {
            if (this.currentState != State.TERMINATED) {
                this.currentState = State.TERMINATED;
                this.endRunnable.run();
            }
            return;
        }

        if (this.checkConditions(this.freezeConditions)) {
            if (this.currentState != State.FROZEN) {
                this.currentState = State.FROZEN;
            }
        }

        if (this.period <= 0) {
            if (this.delay <= 0) {
                if (this.async) {
                    TaskScheduler.getInstance().async(this::tick);
                } else {
                    TaskScheduler.getInstance().sync(this::tick);
                }
            } else {
                if (this.async) {
                    TaskScheduler.getInstance().delayAsync(this::tick, delay);
                } else {
                    TaskScheduler.getInstance().delaySync(this::tick, delay);
                }
            }
        } else {
            if (this.async) {
                TaskScheduler.getInstance().periodicAsync(this::tick, delay, period);
            } else {
                TaskScheduler.getInstance().periodicSync(this::tick, delay, period);
            }
        }
        if (this.currentState == State.RUNNING) {
            this.mainRunnable.run();
        }
    }

    @Override
    public void cancel() {
        this.currentState = State.TERMINATED;
    }

    public enum State {
        UNBORN,
        RUNNING,
        FROZEN,
        TERMINATED
    }
}

package io.github.arenacore.match.task;

import com.google.common.base.Preconditions;
import io.github.arenacore.ArenaCore;
import org.bukkit.Bukkit;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class Task implements IArenaTask {

    private State currentState = State.UNBORN;
    private long period = -1L;
    private long delay = -1L;
    private boolean async = false;

    private Runnable mainRunnable;
    private Runnable endRunnable;

    private final List<BooleanSupplier> freezeConditions = new ArrayList<>();
    private final List<BooleanSupplier> terminateConditions = new ArrayList<>();

    public Task after(long delay) {
        this.delay = delay;
        return this;
    }

    public Task after(Duration delay) {
        this.delay = delay.toMillis() / 50L;
        return this;
    }

    public Task async() {
        this.async = true;
        return this;
    }

    public Task sync() {
        this.async = false;
        return this;
    }

    public Task every(Duration period) {
        this.period = period.toMillis() / 50L;
        return this;
    }

    public Task every(long period) {
        this.period = period;
        return this;
    }

    public Task freezeIf(BooleanSupplier condition) {
        this.freezeConditions.add(condition);
        return this;
    }

    public Task termianteIf(BooleanSupplier condition) {
        this.terminateConditions.add(condition);
        return this;
    }

    public List<BooleanSupplier> getFreezeConditions() {
        return freezeConditions;
    }

    public List<BooleanSupplier> getTerminateConditions() {
        return terminateConditions;
    }

    public boolean checkConditions(List<BooleanSupplier> conditions) {
        return conditions.stream().allMatch(BooleanSupplier::getAsBoolean);
    }

    public Task whenFinished(Runnable endRunnable) {
        this.endRunnable = endRunnable;
        return this;
    }

    public State getCurrentState() {
        return currentState;
    }

    public long getDelay() {
        return delay;
    }

    public long getPeriod() {
        return period;
    }

    public Runnable getEndRunnable() {
        return endRunnable;
    }

    public Runnable getMainRunnable() {
        return mainRunnable;
    }

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
                    Bukkit.getScheduler().runTaskAsynchronously(ArenaCore.getInstance(), this::tick);
                } else {
                    Bukkit.getScheduler().runTask(ArenaCore.getInstance(), this::tick);
                }
            } else {
                if (this.async) {
                    Bukkit.getScheduler().runTaskLaterAsynchronously(ArenaCore.getInstance(), this::tick, delay);
                } else {
                    Bukkit.getScheduler().runTaskLater(ArenaCore.getInstance(), this::tick, delay);
                }
            }
        } else {
            if (this.async) {
                Bukkit.getScheduler().runTaskTimerAsynchronously(ArenaCore.getInstance(), this::tick, delay, period);
            } else {
                Bukkit.getScheduler().runTaskTimer(ArenaCore.getInstance(), this::tick, delay, period);
            }
        }
        if (this.currentState == State.RUNNING) {
            this.mainRunnable.run();
        }
    }

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

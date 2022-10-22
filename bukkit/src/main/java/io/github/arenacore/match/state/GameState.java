package io.github.arenacore.match.state;

import io.github.arenacore.ArenaCore;
import io.github.arenacore.match.IMatch;
import net.minikloon.fsmgasm.State;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class GameState extends State implements Listener {

    protected final Map<String, Listener> listeners = new HashMap<>();
    private final IMatch match;

    protected GameState(IMatch match) {
        super();
        this.match = match;
    }

    @Override
    public final void start() {
        super.start();
        register("self", this);
    }

    @Override
    public final void end() {
        super.end();
        if (!super.getEnded()) return;

        new HashMap<>(listeners).keySet().forEach(this::unregister);
        match.getTaskManager().cancelTask("state-" + hashCode());
    }

    public void delayedTask(String id, Runnable mainRunnable, Runnable cancelRunnable, int delayInTicks) {
        this.match.getTaskManager().delayedTask("state-" + hashCode() + "-" + id, mainRunnable, cancelRunnable, delayInTicks);
    }

    public void delayedTask(String id, Runnable mainRunnable, int delayInTicks) {
        this.match.getTaskManager().delayedTask("state-" + hashCode() + "-" + id, mainRunnable, delayInTicks);
    }

    public void repeatTask(String id, Runnable mainRunnable, Runnable cancelRunnable, int delayInTicks) {
        this.match.getTaskManager().repeatTask("state-" + hashCode() + "-" + id, mainRunnable, cancelRunnable, delayInTicks);
    }

    public void repeatTask(String id, Runnable mainRunnable, int periodInTicks) {
        this.match.getTaskManager().repeatTask("state-" + hashCode() + "-" + id, mainRunnable, periodInTicks);
    }

    public void repeatTask(String id, Runnable mainRunnable, Runnable cancelRunnable, long delayInTicks, long periodInTicks) {
        this.match.getTaskManager().repeatTask("state-" + hashCode() + "-" + id, mainRunnable, cancelRunnable, delayInTicks, periodInTicks);
    }

    public void register(String id, Listener listener) {
        listeners.put(id, listener);
        ArenaCore.getInstance().getServer().getPluginManager().registerEvents(listener, ArenaCore.getInstance());
    }

    public void unregister(String id) {
        Listener listener = listeners.get(id);
        if (listener == null) return;
        listeners.remove(id);
        HandlerList.unregisterAll(listener);
    }

    public IMatch getMatch() {
        return match;
    }

    public Map<String, Listener> getListeners() {
        return listeners;
    }

    @NotNull
    @Override
    public Duration getDuration() {
        return Duration.ZERO;
    }
}

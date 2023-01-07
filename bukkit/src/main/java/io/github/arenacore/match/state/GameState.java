package io.github.arenacore.match.state;

import io.github.arenacore.ArenaCore;
import io.github.arenacore.match.IMatch;
import io.github.arenacore.match.task.TaskGroup;
import net.minikloon.fsmgasm.StateSeries;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

public abstract class GameState extends StateSeries implements Listener {

    protected final Map<String, Listener> listeners = new HashMap<>();
    private final IMatch match;
    private final TaskGroup taskGroup = new TaskGroup();

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

    public TaskGroup getTaskGroup() {
        return taskGroup;
    }

    public IMatch getMatch() {
        return match;
    }

    public Map<String, Listener> getListeners() {
        return listeners;
    }
}

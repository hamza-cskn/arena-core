package io.github.arenacore.match.state;

import io.github.arenacore.ArenaCore;
import io.github.arenacore.match.Match;
import io.github.arenacore.match.task.MatchTaskManager;
import net.minikloon.fsmgasm.State;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

public abstract class GameState extends State implements Listener {

    protected final Map<String, Listener> listeners = new HashMap<>();
    protected final MatchTaskManager matchTaskManager = new MatchTaskManager();
    private final Match match;

    protected GameState(Match match) {
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
        matchTaskManager.cancelTasks();
    }

    protected void register(String id, Listener listener) {
        listeners.put(id, listener);
        ArenaCore.getInstance().getServer().getPluginManager().registerEvents(listener, ArenaCore.getInstance());
    }

    protected void unregister(String id) {
        Listener listener = listeners.get(id);
        if (listener == null) return;
        listeners.remove(id);
        HandlerList.unregisterAll(listener);
    }

    public MatchTaskManager getTaskManager() {
        return matchTaskManager;
    }
}

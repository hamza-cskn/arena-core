package mc.obliviate.arenacore.bukkit.state;

import mc.obliviate.arenacore.bukkit.BukkitArenaCore;
import mc.obliviate.arenacore.match.IMatch;
import mc.obliviate.arenacore.match.state.GameState;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

public abstract class BukkitGameStateImpl extends GameState {

    private final Map<String, Listener> listeners = new HashMap<>();

    protected BukkitGameStateImpl(IMatch match) {
        super(match);
    }

    public void register(String id, Listener listener) {
        if (listeners.containsKey(id)) unregisterListener(id);
        listeners.put(id, listener);
        BukkitArenaCore.getInstance().getServer().getPluginManager().registerEvents(listener, BukkitArenaCore.getInstance());
    }

    public void unregisterListener(String id) {
        Listener listener = listeners.get(id);
        if (listener == null) return;
        listeners.remove(id);
        HandlerList.unregisterAll(listener);
    }
}

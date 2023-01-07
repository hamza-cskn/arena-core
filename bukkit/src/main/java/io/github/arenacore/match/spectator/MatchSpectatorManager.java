package io.github.arenacore.match.spectator;

import io.github.arenacore.match.IMatch;
import io.github.arenacore.user.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MatchSpectatorManager implements ISpectatorManager {

    private final IMatch match;
    private final Map<UUID, ISpectator> spectators = new HashMap<>();

    public MatchSpectatorManager(IMatch match) {
        this.match = match;
    }

    public boolean spectate(IUser user) {
        IPureSpectator spectator = UserHandler.getInstance().switchPureSpectator(user, match);
        spectators.put(user.getPlayer().getUniqueId(), spectator);
        return true;
    }

    public boolean spectate(IMember member) {
        ISemiSpectator spectator = UserHandler.getInstance().switchSemiSpectator(member, match);
        spectators.put(member.getPlayer().getUniqueId(), spectator);
        return true;
    }

    public void unspectate(ISpectator spectator) {
        spectator.unspectate();
        spectators.remove(spectator.getPlayer().getUniqueId());
    }

    public Map<UUID, ISpectator> getSpectators() {
        return Collections.unmodifiableMap(spectators);
    }
}

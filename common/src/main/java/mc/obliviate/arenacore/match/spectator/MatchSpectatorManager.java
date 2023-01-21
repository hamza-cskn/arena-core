package mc.obliviate.arenacore.match.spectator;

import mc.obliviate.arenacore.match.IMatch;
import mc.obliviate.arenacore.user.*;

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
        spectators.put(user.getUniqueId(), spectator);
        return true;
    }

    public boolean spectate(IMember member) {
        ISemiSpectator spectator = UserHandler.getInstance().switchSemiSpectator(member, match);
        spectators.put(member.getUniqueId(), spectator);
        return true;
    }

    public void unspectate(ISpectator spectator) {
        spectator.unspectate();
        spectators.remove(spectator.getUniqueId());
    }

    public Map<UUID, ISpectator> getSpectators() {
        return Collections.unmodifiableMap(spectators);
    }
}

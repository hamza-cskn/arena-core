package mc.obliviate.arenacore.match.spectator;

import mc.obliviate.arenacore.match.Match;
import mc.obliviate.arenacore.user.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MatchSpectatorManager implements SpectatorManager {

    private final Match match;
    private final Map<UUID, Spectator> spectators = new HashMap<>();

    public MatchSpectatorManager(Match match) {
        this.match = match;
    }

    public boolean spectate(User user) {
        PureSpectator spectator = UserHandler.getInstance().switchPureSpectator(user, match);
        spectators.put(user.getUniqueId(), spectator);
        return true;
    }

    public boolean spectate(Member member) {
        SemiSpectator spectator = UserHandler.getInstance().switchSemiSpectator(member, match);
        spectators.put(member.getUniqueId(), spectator);
        return true;
    }

    public void unspectate(Spectator spectator) {
        spectator.unspectate();
        spectators.remove(spectator.getUniqueId());
    }

    public Map<UUID, Spectator> getSpectators() {
        return Collections.unmodifiableMap(spectators);
    }
}

package io.github.arenacore.match.spectator;

import io.github.arenacore.match.Match;
import io.github.arenacore.user.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MatchSpectatorManager {

    private final Match match;
    private final Map<UUID, ISpectator> spectators = new HashMap<>();

    public MatchSpectatorManager(Match match) {
        this.match = match;
    }

    public boolean spectate(User user) {
        PureSpectator spectator = UserHandler.switchPureSpectator(user, match);
        spectators.put(user.getPlayer().getUniqueId(), spectator);
        return true;
    }

    public boolean spectate(Member member) {
        SemiSpectator spectator = UserHandler.switchSemiSpectator(member, match);
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

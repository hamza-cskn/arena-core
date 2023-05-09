package mc.obliviate.arenacore.match.spectator;

import mc.obliviate.arenacore.user.Member;
import mc.obliviate.arenacore.user.Spectator;
import mc.obliviate.arenacore.user.User;

import java.util.Map;
import java.util.UUID;

public interface SpectatorManager {

    boolean spectate(User user);

    boolean spectate(Member member);

    void unspectate(Spectator spectator);

    Map<UUID, Spectator> getSpectators();


}

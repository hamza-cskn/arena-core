package mc.obliviate.arenacore.match.spectator;

import mc.obliviate.arenacore.user.IMember;
import mc.obliviate.arenacore.user.ISpectator;
import mc.obliviate.arenacore.user.IUser;

import java.util.Map;
import java.util.UUID;

public interface ISpectatorManager {

    boolean spectate(IUser user);

    boolean spectate(IMember member);

    void unspectate(ISpectator spectator);

    Map<UUID, ISpectator> getSpectators();


}

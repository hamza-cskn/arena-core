package io.github.arenacore.match.spectator;

import io.github.arenacore.user.IMember;
import io.github.arenacore.user.ISpectator;
import io.github.arenacore.user.IUser;

import java.util.Map;
import java.util.UUID;

public interface ISpectatorManager {

    boolean spectate(IUser user);

    boolean spectate(IMember member);

    void unspectate(ISpectator spectator);

    Map<UUID, ISpectator> getSpectators();


}

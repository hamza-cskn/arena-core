package io.github.arenacore.user;

import io.github.arenacore.match.IMatch;

public interface ISpectator extends IUser {

    void spectate(IMatch match);

    void unspectate();

}

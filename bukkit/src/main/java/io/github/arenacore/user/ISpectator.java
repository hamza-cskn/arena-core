package io.github.arenacore.user;

import io.github.arenacore.match.Match;

public interface ISpectator extends IUser {

    void spectate(Match match);

    void unspectate();

}

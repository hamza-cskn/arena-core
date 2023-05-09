package mc.obliviate.arenacore.user;

import mc.obliviate.arenacore.match.Match;

public interface Spectator extends User {

    void spectate(Match match);

    void unspectate();

}

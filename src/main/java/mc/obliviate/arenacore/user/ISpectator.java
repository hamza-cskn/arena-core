package mc.obliviate.arenacore.user;

import mc.obliviate.arenacore.match.IMatch;

public interface ISpectator extends IUser {

    void spectate(IMatch match);

    void unspectate();

}

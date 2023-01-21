package mc.obliviate.arenacore.user;

import mc.obliviate.arenacore.match.IMatch;

public interface IMember extends IUser {

    IMatch getMatch();

}

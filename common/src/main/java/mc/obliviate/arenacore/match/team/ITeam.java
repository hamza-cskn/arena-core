package mc.obliviate.arenacore.match.team;

import mc.obliviate.arenacore.match.IMatch;
import mc.obliviate.arenacore.user.IMember;

import java.util.Set;

public interface ITeam {

    IMatch getMatch();

    Set<IMember> getMembers();

    void register(IMember member);

    void unregister(IMember member);

}

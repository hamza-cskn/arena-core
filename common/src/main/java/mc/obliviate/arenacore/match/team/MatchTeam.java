package mc.obliviate.arenacore.match.team;

import mc.obliviate.arenacore.match.IMatch;
import mc.obliviate.arenacore.user.IMember;

import java.util.HashSet;
import java.util.Set;

public class MatchTeam implements ITeam {

    private final IMatch match;
    private final Set<IMember> members = new HashSet<>();

    public MatchTeam(IMatch match) {
        this.match = match;
    }

    @Override
    public void register(IMember member) {
        members.add(member);
    }

    @Override
    public void unregister(IMember member) {
        members.remove(member);
    }

    @Override
    public Set<IMember> getMembers() {
        return members;
    }

    @Override
    public IMatch getMatch() {
        return this.match;
    }
}

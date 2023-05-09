package mc.obliviate.arenacore.match.team;

import mc.obliviate.arenacore.match.Match;
import mc.obliviate.arenacore.user.Member;

import java.util.HashSet;
import java.util.Set;

public class MatchTeam implements Team {

    private final Match match;
    private final Set<Member> members = new HashSet<>();

    public MatchTeam(Match match) {
        this.match = match;
    }

    @Override
    public void register(Member member) {
        members.add(member);
    }

    @Override
    public void unregister(Member member) {
        members.remove(member);
    }

    @Override
    public Set<Member> getMembers() {
        return members;
    }

    @Override
    public Match getMatch() {
        return this.match;
    }
}

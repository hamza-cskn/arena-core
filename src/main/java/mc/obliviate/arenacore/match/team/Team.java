package mc.obliviate.arenacore.match.team;

import mc.obliviate.arenacore.match.Match;
import mc.obliviate.arenacore.user.Member;

import java.util.Set;

public interface Team {

    Match getMatch();

    Set<Member> getMembers();

    void register(Member member);

    void unregister(Member member);

}

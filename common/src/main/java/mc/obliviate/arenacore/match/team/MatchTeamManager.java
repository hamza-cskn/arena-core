package mc.obliviate.arenacore.match.team;

import mc.obliviate.arenacore.match.IMatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatchTeamManager {

    private final IMatch match;
    private final List<ITeam> teams;

    public MatchTeamManager(IMatch match) {
        this.match = match;
        this.teams = new ArrayList<>();
    }

    public void createEmptyTeams(int amount) {
        for (int i = 0; i < amount; i++) {
            this.teams.add(new MatchTeam(match));
        }
    }

    public void register(ITeam team) {
        this.teams.add(team);
    }

    public void unregister(ITeam team) {
        this.teams.remove(team);
    }

    public ITeam getTeam(int index) {
        return this.teams.get(index);
    }

    public List<ITeam> getTeams() {
        return Collections.unmodifiableList(teams);
    }

    public IMatch getMatch() {
        return this.match;
    }
}
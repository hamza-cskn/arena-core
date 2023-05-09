package mc.obliviate.arenacore.match.team;

import mc.obliviate.arenacore.match.Match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatchTeamManager {

    private final Match match;
    private final List<Team> teams;

    public MatchTeamManager(Match match) {
        this.match = match;
        this.teams = new ArrayList<>();
    }

    public void createEmptyTeams(int amount) {
        for (int i = 0; i < amount; i++) {
            this.teams.add(new MatchTeam(match));
        }
    }

    public void register(Team team) {
        this.teams.add(team);
    }

    public void unregister(Team team) {
        this.teams.remove(team);
    }

    public Team getTeam(int index) {
        return this.teams.get(index);
    }

    public List<Team> getTeams() {
        return Collections.unmodifiableList(teams);
    }

    public Match getMatch() {
        return this.match;
    }
}
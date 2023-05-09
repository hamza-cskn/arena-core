package mc.obliviate.arenacore.match.state;

import mc.obliviate.arenacore.match.Match;
import mc.obliviate.arenacore.match.task.MatchTaskGroup;
import mc.obliviate.arenacore.task.TaskGroup;
import net.minikloon.fsmgasm.State;

public abstract class GameState extends State {

    private final Match match;
    private final TaskGroup matchTaskGroup = new MatchTaskGroup();

    public GameState(Match match) {
        super();
        this.match = match;
    }

    @Override
    public final void start() {
        super.start();
    }

    @Override
    public final void end() {
        super.end();
        if (!super.getEnded()) return;

        match.getTaskManager().cancel();
    }

    public TaskGroup getTaskGroup() {
        return matchTaskGroup;
    }

    public Match getMatch() {
        return match;
    }

}

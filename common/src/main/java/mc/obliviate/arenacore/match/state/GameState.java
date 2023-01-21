package mc.obliviate.arenacore.match.state;

import mc.obliviate.arenacore.match.IMatch;
import mc.obliviate.arenacore.match.task.TaskGroup;
import net.minikloon.fsmgasm.State;

public abstract class GameState extends State {

    private final IMatch match;
    private final TaskGroup taskGroup = new TaskGroup();

    protected GameState(IMatch match) {
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
        return taskGroup;
    }

    public IMatch getMatch() {
        return match;
    }

}

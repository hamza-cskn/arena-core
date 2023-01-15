package mc.obliviate.arenacore.match.state;

import mc.obliviate.arenacore.match.task.TaskGroup;
import net.minikloon.fsmgasm.StateSeries;

public class ScheduledStateSeries extends StateSeries {

    private final TaskGroup taskGroup = new TaskGroup();
    private final int interval;

    public ScheduledStateSeries(int interval) {
        this.interval = interval;
    }

    @Override
    protected void onStart() {
        taskGroup.newTask("heart-beat").every(interval).run(this::update);
    }

    public TaskGroup getTaskGroup() {
        return taskGroup;
    }
}

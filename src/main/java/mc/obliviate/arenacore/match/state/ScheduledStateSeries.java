package mc.obliviate.arenacore.match.state;

import mc.obliviate.arenacore.task.TaskGroup;
import mc.obliviate.arenacore.task.TaskManager;
import net.minikloon.fsmgasm.StateSeries;

public class ScheduledStateSeries extends StateSeries {

    private final TaskGroup taskGroup = TaskManager.getInstance().createTaskGroup();
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

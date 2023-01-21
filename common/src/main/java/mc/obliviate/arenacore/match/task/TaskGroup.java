package mc.obliviate.arenacore.match.task;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TaskGroup implements IArenaTaskGroup {

    private static final Set<TaskGroup> TASK_GROUP_SET = new HashSet<>();
    private final Map<String, IArenaTask> tasks = new HashMap<>();

    public TaskGroup() {
        TASK_GROUP_SET.add(this);
    }

    private void registerTask(String taskName, IArenaTask task) {
        tasks.put(taskName, task);
    }

    /**
     * task names defines purpose of task
     * and task manager can cancel tasks using
     * their names.
     * <p>
     * task manager cancels tasks with checking their
     * first part of names. for example,
     * <p>
     * <b>TASK 1:</b> round.end-timer-task<br>
     * <b>TASK 2:</b> round.ending-task<br>
     * <b>TASK 3:</b> round.scoreboard-task
     * <p>
     * when you call cancelTasks("round")
     * task manager checks first part of
     * the tasks, and it will cancel all of them.
     * <p>
     * when you call cancelTasks("round.e")
     * task manager will cancel all tasks
     * except "round.scoreboard-task"
     */
    public Task newTask(String taskName) {
        Task task = new Task();
        registerTask(taskName, task);
        return task;
    }

    public void cancelTask(String prefix) {
        tasks.forEach((taskName, task) -> {
            if (taskName.startsWith(prefix)) {
                task.cancel();
            }
        });
    }

    /**
     * Cancels all tasks of the task group.
     */
    @Override
    public void cancel() {
        tasks.values().forEach(IArenaTask::cancel);
    }

    public void terminate() {
        this.cancel();
        TASK_GROUP_SET.remove(this);
    }
}
